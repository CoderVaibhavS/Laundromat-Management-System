import java.util.Date;

public class Wash_Cycle extends Student {
    private String washId;
    private float weight;
//    status 1 for clothes ready
    private boolean status;
    Date placeDate;    /* Date.now() when the wash order is placed */
    Date expDelDate;   /* Date estimated by admin after the order is collected */

    Wash_Cycle(String name, String id, String hostel) {
        super(name, id, hostel);
        super.washNo += 1;
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
