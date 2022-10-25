import java.util.Date;

public class Wash_Cycle extends StudentAccount {
    protected int washNo=0;
    protected String washId;// private to protected
    protected float weight;
    protected boolean status;  // status 1 for clothes ready
    protected boolean received; // true when laundry received
    protected Date placeDate;    /* Date.now() when the wash order is placed */
    protected Date expDelDate;   /* Date estimated by admin after the order is collected (+2 days in this case) */

    Wash_Cycle(String name, String id, String hostel, String planName) {
        super(name, id, hostel, planName);
        washNo = super.totalWashes + 1;
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
