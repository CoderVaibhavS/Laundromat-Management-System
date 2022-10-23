import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

interface Student_Methods {
    public void updateAddCharge();      // adding 20% extra if extra cycles are used
    public void dropLaundry(float weight);
    public void receiveLaundry(Wash_Cycle receivedCycle);
    public void refreshCycles();
}

public class Student extends Wash_Plan implements Student_Methods {
    public ArrayList<Wash_Cycle> listOfWash_Cycles = new ArrayList<>();
    private String name;
    private String id;
    private String hostel;
    private int balance;    // total semester balance including the plan and additional charges
    private int addCharge;  // charged 20% extra in case of exceeding the no of washes
    protected int washNo = 0;
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
        cycle.washId = this.id + cycle.placeDate.toString();
        // cal.add(Calendar.DAY_OF_MONTH, 2);
        cal.add(Calendar.SECOND, 4);
        cycle.expDelDate = cal.getTime();
        washCyclesLeft--;
        listOfWash_Cycles.add(cycle);
        updateAddCharge();
        if(weight > 6) {
//            if weight limit exceeded, count an extra cycle
            washCyclesLeft--;
            updateAddCharge();
        }
    }
    
    public void receiveLaundry(Wash_Cycle receivedCycle) {
        for(Wash_Cycle cycle:listOfWash_Cycles){
            if(cycle.equals(receivedCycle)&&cycle.status){
                cycle.received = true;
            }
        }
    }

    public void refreshCycles() {
        for(Wash_Cycle cycle : listOfWash_Cycles){
            if(Calendar.getInstance().getTime().after(cycle.expDelDate))
                cycle.status = true;
        }
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