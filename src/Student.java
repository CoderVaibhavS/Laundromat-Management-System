import java.util.*;

interface LaundryAccount{
    void updateAddCharge();      // adding 20% extra if extra cycles are used
}

interface Laundry {
    void dropLaundry(float weight);
    void receiveLaundry(Wash_Cycle receivedCycle);
    void refreshCycles();
}

public class Student extends User implements Laundry {
    public ArrayList<Wash_Cycle> listOfWash_Cycles = new ArrayList<>();
    final private String name;
    final String id;
    final String hostel;
    Wash_Plan plan;

    public String getName() {
        return this.name;
    }

    public String getId() {
        return this.id;
    }

    public String getHostel() {
        return this.hostel;
    }

    private int balance;    // total semester balance including the plan and additional charges
    private int addCharge;  // charged 20% extra in case of exceeding the no of washes
    protected int totalWashes = 0;
    private int washCyclesLeft;
//    Wash_Plan plan;


    public int getBalance() {
        return this.balance;
    }

    public int getAddCharge() { return this.addCharge; }

    public void updateAddCharge() {
        if(washCyclesLeft < 0) {
            balance += 1.2*plan.getRatePerCycle();
            addCharge += 1.2*plan.getRatePerCycle();
        }
    }

    public void dropLaundry(float weight) {

        Wash_Cycle cycle = new Wash_Cycle(id);
        
        cycle.weight = weight;
        cycle.status = false;
        cycle.received = false;
        // Calendar cal = Calendar.getInstance();
        // cal.setTime(new Date());
        // cycle.placeDate = cal.getTime();
        totalWashes++;
        cycle.washId = id + ((totalWashes<=9)?"00":"0") + Integer.toString(totalWashes);
        // cal.add(Calendar.DAY_OF_MONTH, 2);
        // cal.add(Calendar.SECOND, 4);
        // cycle.expDelDate = cal.getTime();
        washCyclesLeft--;
        updateAddCharge();
        if(weight > 6) {
            //            if weight limit exceeded, count an extra cycle
            totalWashes++;
            washCyclesLeft--;
            updateAddCharge();
        }
        System.out.println(name + " dropped the laundry:" + cycle.weight);
        cycle.scheduleDel();
        listOfWash_Cycles.add(cycle);
    }
    
    public void receiveLaundry(Wash_Cycle receivedCycle) {
        for(Wash_Cycle cycle:listOfWash_Cycles){
            if(cycle.equals(receivedCycle)&&cycle.status){
                cycle.received = true;
            }
        }
        System.out.println(name + " received the laundry: " + receivedCycle.washId);
    }

    public void refreshCycles() {
        for(Wash_Cycle cycle : listOfWash_Cycles){
            if(Calendar.getInstance().getTime().after(cycle.expDelDate))
                cycle.status = true;
        }
        System.out.println("refreshed cycles for "+ getName());
    }

    Student(String name, String id, String hostel, String planName, String password) {
        super(id,password);
        this.name = name;
        this.id = id;
        this.hostel = hostel;
        this.plan = new Wash_Plan(planName);
//        this.plan = Admin.PlansList.getPlan(planName);
        this.balance = plan.getRatePerCycle() * plan.getNoOfCycles();
        washCyclesLeft = plan.getNoOfCycles();
    }
}