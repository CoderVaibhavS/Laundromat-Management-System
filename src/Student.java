import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
public class Student {
    public ArrayList<Wash_Cycle> listOfWash_Cycles = new ArrayList<>();
    private String name;
    private String id;
    private String hostel;
    private int balance;
    protected int washNo = 0;
    private int washCyclesLeft;
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

    public int getBalance() {
        return this.balance;
    }

    public void dropLaundry(float weight){

        Wash_Cycle cycle = new Wash_Cycle(name, id, hostel, plan.getName());
        cycle.weight = weight;
        cycle.status=false;
        cycle.recieved=false;
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cycle.placeDate = cal.getTime();
        cycle.washId = this.id + cycle.placeDate.toString();
        // cal.add(Calendar.DAY_OF_MONTH, 2);
        cal.add(Calendar.SECOND, 4);
        cycle.expDelDate = cal.getTime();
        washCyclesLeft--;
        listOfWash_Cycles.add(cycle);
        if(washCyclesLeft<0){
            balance+=1.2*plan.getRatePerCycle();// adding 20% extra if extra cycles are used
        }
    }
    
    public void recieveLaundry(Wash_Cycle recievedCycle){
        for(Wash_Cycle cycle:listOfWash_Cycles){
            if(cycle.equals(recievedCycle)&&cycle.status){
                cycle.recieved=true;
            }
        }
    }

    public void refreshCycles(){
        for(Wash_Cycle cycle : listOfWash_Cycles){
            if(Calendar.getInstance().getTime().after(cycle.expDelDate))
            cycle.status = true;
        }
    }

    Student(String name, String id, String hostel, String planName) {
        this.name = name;
        this.id = id;
        this.hostel = hostel;
        this.balance = 0;
        this.plan = Admin.PlansList.getPlan(planName);
        washCyclesLeft = plan.getNoOfCycles();
    }
}
