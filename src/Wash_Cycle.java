import java.util.Date;
import java.util.Calendar;

public class Wash_Cycle{
    Student associatedStudent;
    protected int washNo = 0;
    protected String washId;// private to protected
    protected float weight;
    protected boolean washStatus = true;  // status 1 for clothes ready
    protected boolean dryStatus=  true;
    protected boolean F_or_I_Status = false;
    protected boolean received = false; // true when laundry received
    protected Date placeDate;    /* Date.now() when the wash order is placed */
    protected Date expDelDate;   /* Date estimated by admin after the order is collected (+2 days in this case) */
    Wash_Cycle(String id, float weight) {
        associatedStudent = Admin.students.get(id);
        washNo = associatedStudent.totalWashes + 1;
        delTime = HostelDelTime.hostelDelTime.get(associatedStudent.getHostel());
        this.weight = weight;
    }
    private int delTime;

    protected void scheduleDel(){
        
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

    String getWashId() {
        return this.washId;
    }

    float getWeight() {
        return this.weight;
    }
}
