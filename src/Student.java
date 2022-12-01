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

public class Student extends User implements Laundry, Serializable {
    public ArrayList<Wash_Cycle> listOfWash_Cycles = new ArrayList<>();
    final private String name;
    final String id;
    final String hostel;
    Wash_Plan plan;
    private int balance;    // total semester balance including the plan and additional charges
    private int addCharge;  // charged 20% extra in case of exceeding the no of washes
    protected int totalWashes = 0;
    private int washCyclesLeft;

    Student(String name, String id, String hostel, String planName, String password) {
        super(id,password);
        this.name = name;
        this.id = id;
        this.hostel = hostel;
        this.plan = Admin.PlansList.getPlan(planName);
        this.balance = plan.getRatePerCycle() * plan.getNoOfCycles();
        washCyclesLeft = plan.getNoOfCycles();
        Admin.updateRevenue(plan.getRatePerCycle()*plan.getNoOfCycles());
    }

    public String getName() { return name; }

    public String getId() { return id; }

    public String getHostel() { return hostel; }

    public String toString() { return name + " " + id; }

    public int getBalance() { return balance; }

    public int getAddCharge() { return this.addCharge; }

    // charged extra for extra wash used
    public void updateAddCharge(int extraCharge) {
        balance += extraCharge;
        addCharge += extraCharge;
        Admin.updateRevenue(extraCharge);
        Admin.StudentsList.updateStudents();
    }

    public void dropLaundry(float weight) {

        Wash_Cycle cycle = new Wash_Cycle(id, weight);
        totalWashes++;
        cycle.washId = id + ((totalWashes<=9)?"00":"0") + Integer.toString(totalWashes);
        washCyclesLeft--;
        int extraCharge = 0;
        if (washCyclesLeft < 0)
            extraCharge += (int)1.2*plan.getRatePerCycle();

        if(weight > 6) {
            // if weight limit exceeded, charge extra
            float extraWeight = weight - plan.getWeight();
            extraCharge += (int)(extraWeight * plan.getRatePerCycle()*1.2/plan.getWeight());
            System.out.println("You dropped " + extraWeight + " kg extra, so you are charged Rs" + extraCharge + " extra.");
        }
        updateAddCharge(extraCharge);

        System.out.print("Dropped the laundry succesfully!");
        cycle.scheduleDel();
        listOfWash_Cycles.add(cycle);

        if(Calendar.getInstance().get(Calendar.DAY_OF_WEEK) == 1 && Admin.weekNo == Admin.weeklyRecord.size() || Admin.weeklyRecord.size() == 0) {
            HashMap map = new HashMap<String, Integer>();
            map.put(id,1);
            Admin.weeklyRecord.add(map);
        }
        else {
            if(Admin.weeklyRecord.get(Admin.weekNo).get(id) == null) {
                Admin.weeklyRecord.get(Admin.weekNo).put(id, 1);
            }
            else {
                Admin.weeklyRecord.get(Admin.weekNo).replace(id, Admin.weeklyRecord.get(Admin.weekNo).get(id)+1);
            }
        }
        Admin.updateWeeklyRecord();

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
}