import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

interface LaundryAccount{
    void updateAddCharge();      // adding 20% extra if extra cycles are used
}

interface Laundry {
    void dropLaundry(float weight);
    void receiveLaundry(Wash_Cycle receivedCycle);
    void refreshCycles();
}

public class Student extends User implements Laundry, Serializable, Runnable {
    public ArrayList<Wash_Cycle> listOfWash_Cycles = new ArrayList<>();
    final private String name;
    final String id;
    final String hostel;
    Wash_Plan plan;
    Thread studentThread;
    public String getName() {
        return this.name;
    }

    public String getId() {
        return this.id;
    }

    public String getHostel() {
        return this.hostel;
    }

    public String toString() {
        return getName() + " " + getId();
    }

    private int balance;    // total semester balance including the plan and additional charges
    private int addCharge;  // charged 20% extra in case of exceeding the no of washes
    protected int totalWashes = 0;
    private int washCyclesLeft;
//    Wash_Plan plan;

    Student(String name, String id, String hostel, String planName, String password) {
        super(id,password);
        this.name = name;
        this.id = id;
        this.hostel = hostel;
//        this.plan = new Wash_Plan(planName);
        this.plan = Admin.PlansList.getPlan(planName);
        this.balance = plan.getRatePerCycle() * plan.getNoOfCycles();
        washCyclesLeft = plan.getNoOfCycles();
        studentThread = new Thread(this, "Student thread " + id);
    }

    public int getBalance() {
        return this.balance;
    }

    public int getAddCharge() { return this.addCharge; }

    public void updateAddCharge() {
        if(washCyclesLeft < 0) {
            balance += 1.2*plan.getRatePerCycle();
            addCharge += 1.2*plan.getRatePerCycle();
        }
        Admin.StudentsList.updateStudents();

    }

    public void dropLaundry(float weight) {

        Wash_Cycle cycle = new Wash_Cycle(id, weight);
        totalWashes++;
        cycle.washId = id + ((totalWashes<=9)?"00":"0") + Integer.toString(totalWashes);
        washCyclesLeft--;
        updateAddCharge();
        if(weight > 6) {
            // if weight limit exceeded, charge extra
            float extraWeight = weight - 6;
            int extraCharge = (int)(extraWeight * plan.getRatePerCycle()*1.2/6);
            addCharge += extraCharge;
            System.out.println("You dropped " + extraWeight + " kg extra, so you are charged Rs" + extraCharge + " extra.");
        }
        System.out.print("Dropped the laundry succesfully! ");
        cycle.scheduleDel();
        listOfWash_Cycles.add(cycle);

        if(Calendar.getInstance().get(Calendar.WEEK_OF_YEAR) > Admin.yearWeekNo) {
            HashMap map = new HashMap<String, Integer>();
            map.put(id,1);
            Admin.weeklyRecord.add(map);
            Admin.yearWeekNo++;
            Admin.weekNo++;
        }
        else {
            if(Admin.weeklyRecord.get(Admin.weekNo - 1).get(id) == null) {
                Admin.weeklyRecord.get(Admin.weekNo - 1).put(id, 1);
            }
            else {
                Admin.weeklyRecord.get(Admin.weekNo - 1).replace(id, Admin.weeklyRecord.get(Admin.weekNo - 1).get(id)+1);
            }
        }

        Admin.StudentsList.updateStudents();
    }

    public void receiveLaundry(Wash_Cycle receivedCycle) {
        for(Wash_Cycle cycle:listOfWash_Cycles){
            if(cycle.equals(receivedCycle) && cycle.washStatus){
                cycle.received = true;
            }
        }
        Admin.StudentsList.updateStudents();
        System.out.println(name + " received the laundry: " + receivedCycle.washId);
    }

    public void refreshCycles() {
        for(Wash_Cycle cycle : listOfWash_Cycles){
            if(Calendar.getInstance().getTime().after(cycle.expDelDate))
                cycle.washStatus = true;
        }
        Admin.StudentsList.updateStudents();
        System.out.println("refreshed cycles for "+ getName());
    }

    @Override
    public void run() {

    }
}