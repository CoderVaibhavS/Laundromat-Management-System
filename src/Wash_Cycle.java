import java.io.Serializable;
import java.util.Date;
import java.util.Calendar;

public class Wash_Cycle implements Serializable {
    Student associatedStudent;
    private int washNo = 0;
    protected String washId;// private to protected
    private float weight;
    private boolean washStatus = false;  // status 1 for clothes ready
    private boolean dryStatus = false;
    private boolean F_or_I_Status = false;
    protected boolean received = false; // true when laundry received
    private int additionalCharge;
    protected Date placeDate;    /* Date.now() when the wash order is placed */
    protected Date expDelDate;   /* Date estimated by admin after the order is collected (+2 days in this case) */
    Wash_Cycle(String id, float weight) {
        associatedStudent = Admin.students.get(id);
        washNo = associatedStudent.totalWashes + 1;
        delTime = HostelDelTime.hostelDelTime.get(associatedStudent.getHostel());
        this.weight = weight;
    }
    private int delTime;

    public void scheduleDel(){
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        placeDate=cal.getTime();
        if(placeDate.getHours()>=12){
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }
        cal.add(Calendar.DAY_OF_MONTH, 2);
        expDelDate = cal.getTime();
        expDelDate.setHours(delTime);
        expDelDate.setMinutes(0);
        expDelDate.setSeconds(0);
        System.out.println("Expected Delivery by: " + expDelDate.toString());
    }

    public void setAdditionalCharge(int amount) { additionalCharge = amount; }

    String getWashId() {
        return this.washId;
    }
    boolean getWashStatus() { return washStatus; }
    boolean getDryStatus() { return dryStatus; }
    boolean getIronOrFoldStatus() { return F_or_I_Status; }
    float getWeight() {
        return this.weight;
    }

    public String toString() {
        if (received)
            return "Laundry dropped on " + placeDate + " has been delivered and costs " + associatedStudent.plan.getRatePerCycle() + " including Rs " + additionalCharge + "extra charges.";
        else if(!washStatus)
            return "Laundry dropped on " + placeDate + " is under washing and costs " + associatedStudent.plan.getRatePerCycle() + " including Rs " + additionalCharge + "extra charges.";
        else if(!dryStatus)
            return "Laundry dropped on " + placeDate + " is under drying and costs " + associatedStudent.plan.getRatePerCycle() + " including Rs " + additionalCharge + "extra charges.";
        else if(associatedStudent.plan.ironORfold())
            return "Laundry dropped on " + placeDate + " is under ironing and costs " + associatedStudent.plan.getRatePerCycle() + " including Rs " + additionalCharge + "extra charges.";
        else
            return "Laundry dropped on " + placeDate + " is under folding and costs " + associatedStudent.plan.getRatePerCycle() + " including Rs " + additionalCharge + "extra charges.";
    }
}
