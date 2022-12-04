import java.io.Serializable;
import java.util.Date;

public class Wash_Cycle implements Serializable {
    Student associatedStudent;
    private int washNo = 0;
    protected String washId;// private to protected
    private float weight;
    private boolean washStatus = false;  // status 1 for clothes ready
    private boolean dryStatus = false;
    private boolean F_or_I_Status = false;
    private boolean received = false; // true when laundry received
    private boolean onDelivery = false;
    private int additionalCharge;
    public Date placeDate;    /* Date.now() when the wash order is placed */
    private Date expDelDate;   /* Date estimated by admin after the order is collected (+2 days in this case) */
    Wash_Cycle(String id, float weight, Date date) {
        associatedStudent = Admin.students.get(id);
        washNo = associatedStudent.totalWashes + 1;
//        delTime = HostelDelTime.hostelDelTime.get(associatedStudent.getHostel());
        this.weight = weight;
        this.placeDate = date;
    }
    private int delTime;

    public void scheduleDel(){
        
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(new Date());
//        placeDate=cal.getTime();
//        if(placeDate.getHours()>=12){
//            cal.add(Calendar.DAY_OF_MONTH, 1);
//        }
//        cal.add(Calendar.DAY_OF_MONTH, 2);
//        expDelDate = cal.getTime();
//        expDelDate.setHours(delTime);
//        expDelDate.setMinutes(0);
//        expDelDate.setSeconds(0);
//        System.out.println("Expected Delivery by: " + expDelDate.toString());
        System.out.println("Expected delivery by: " + HostelSchedule.hostelDropDay.get(associatedStudent.getHostel()) + " at " + HostelSchedule.hostelDelTime.get(associatedStudent.getHostel()));
    }

    public void setAdditionalCharge(int amount) { additionalCharge = amount; }

    String getWashId() {
        return this.washId;
    }
    boolean getWashStatus() { return washStatus; }
    boolean getDryStatus() { return dryStatus; }
    boolean getIronOrFoldStatus() { return F_or_I_Status; }
    boolean isOnDelivery() { return onDelivery; }
    boolean isDelivered() { return received; }
    void updateStatus(String s) {
        if(s.equals("WASH")) washStatus = true;
        else if(s.equals("DRY")) dryStatus = true;
        else if(s.equals("F_OR_I")) F_or_I_Status = true;
        else if (s.equals("ONDELIVERY")) onDelivery = true;
        else received = true;
    }
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
