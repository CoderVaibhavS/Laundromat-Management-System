import java.util.Date;
import java.util.Calendar;

public class Wash_Cycle extends Student{
    protected int washNo=0;
    protected String washId;// private to protected
    protected float weight;
    protected boolean status;  // status 1 for clothes ready
    protected boolean received; // true when laundry received
    protected Date placeDate;    /* Date.now() when the wash order is placed */
    protected Date expDelDate;   /* Date estimated by admin after the order is collected (+2 days in this case) */
    private int delTime = HostelDelTime.hostelDelTime.get(this.getHostel());
    Wash_Cycle(String name, String id, String hostel, String planName) {
        super(name, id, hostel, planName);
        washNo = super.totalWashes + 1;
    }

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
        System.out.println("Expected Delivery by: "+expDelDate.toString());
    }

    String getWashId() {
        return this.washId;
    }

    float getWeight() {
        return this.weight;
    }

    boolean isStatus() {
        return this.status;
    }
}
