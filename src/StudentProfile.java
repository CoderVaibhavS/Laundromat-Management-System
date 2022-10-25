import java.util.ArrayList;

public class StudentProfile extends Wash_Plan {
    public ArrayList<Wash_Cycle> listOfWash_Cycles = new ArrayList<>();
    private String name;
    private String id;
    private String hostel;



    public String getName() {
        return this.name;
    }

    public String getId() {
        return this.id;
    }

    public String getHostel() {
        return this.hostel;
    }

    StudentProfile(String name, String id, String hostel, String planName) {
        super(planName);
        this.name = name;
        this.id = id;
        this.hostel = hostel;
    }
}
