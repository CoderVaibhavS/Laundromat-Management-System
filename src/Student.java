import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

interface LaundryAccount{
    public void updateAddCharge();      // adding 20% extra if extra cycles are used
}

interface Laundry {
    public void dropLaundry(float weight);
    public void receiveLaundry(Wash_Cycle receivedCycle);
    public void refreshCycles();
}

public class Student extends Wash_Plan implements Laundry {
    public ArrayList<Wash_Cycle> listOfWash_Cycles = new ArrayList<>();
    private String name;
    private String id;
    private String hostel;
    private int balance;    // total semester balance including the plan and additional charges
    private int addCharge;  // charged 20% extra in case of exceeding the no of washes
    protected int totalWashes = 0;
    private int washCyclesLeft;
//    Wash_Plan plan;


    public String getName() {
        return this.name;
    }

    public String getId() {
        return this.id;
    }

    public String getHostel() {
        return this.hostel;
    }

    public int getBalance() {
        return this.balance;
    }

    public int getAddCharge() { return this.addCharge; }

    public void updateAddCharge() {
        if(washCyclesLeft < 0) {
            balance += 1.2*this.getRatePerCycle();
            addCharge += 1.2*this.getRatePerCycle();
        }
    }

    public void dropLaundry(float weight) {

        Wash_Cycle cycle = new Wash_Cycle(name, id, hostel, super.getName());
        
        cycle.weight = weight;
        cycle.status = false;
        cycle.received = false;
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cycle.placeDate = cal.getTime();
        totalWashes++;
        cycle.washId = this.id + ((totalWashes<=9)?"00":"0") + Integer.toString(totalWashes);
        // cal.add(Calendar.DAY_OF_MONTH, 2);
        cal.add(Calendar.SECOND, 4);
        cycle.expDelDate = cal.getTime();
        washCyclesLeft--;
        listOfWash_Cycles.add(cycle);
        updateAddCharge();
        if(weight > 6) {
//            if weight limit exceeded, count an extra cycle
            totalWashes++;
            washCyclesLeft--;
            updateAddCharge();
        }
        System.out.println(this.name+" dropped the laundry:"+cycle.weight);
    }
    
    public void receiveLaundry(Wash_Cycle receivedCycle) {
        for(Wash_Cycle cycle:listOfWash_Cycles){
            if(cycle.equals(receivedCycle)&&cycle.status){
                cycle.received = true;
            }
        }
        System.out.println(this.name+"recieved the laundry: "+receivedCycle.washId);
    }

    public void refreshCycles() {
        for(Wash_Cycle cycle : listOfWash_Cycles){
            if(Calendar.getInstance().getTime().after(cycle.expDelDate))
                cycle.status = true;
        }
        System.out.println("refreshed cycles for "+ this.name);
    }

    Student(String name, String id, String hostel, String planName) {
        super(planName);
        this.name = name;
        this.id = id;
        this.hostel = hostel;
//        this.plan = Admin.PlansList.getPlan(planName);
        this.balance = this.getRatePerCycle() * this.getNoOfCycles();
        washCyclesLeft = this.getNoOfCycles();
    }
}