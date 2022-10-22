public class Student {
    private String name;
    private String id;
    private String hostel;
    private int balance;
    protected int washNo = 0;
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

    Student(String name, String id, String hostel, String planName) {
        this.name = name;
        this.id = id;
        this.hostel = hostel;
        this.balance = 0;
        this.plan = Admin.PlansList.getPlan(planName);
    }
}
