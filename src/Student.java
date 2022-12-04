import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

interface LaundryAccount{
    void updateAddCharge();      // adding 20% extra if extra cycles are used
}

interface Laundry {
    void dropLaundry(float weight, Date date);
    void receiveLaundry(Wash_Cycle receivedCycle);
}

public class Student extends User implements Laundry, Runnable, Serializable {
    public ArrayList<Wash_Cycle> listOfWash_Cycles = new ArrayList<>();
    final private String name;
    final String id;
    final String hostel;
    Wash_Plan plan;
    private int balance;    // total semester balance including the plan and additional charges
    private int addCharge;  // charged 20% extra in case of exceeding the no of washes
    protected int totalWashes = 0;
    private int washCyclesLeft;
    Thread t;
    public String func;

    Student(String name, String id, String hostel, String planName, String password) {
        super(id.toUpperCase(),password);
        this.name = name.toUpperCase();
        this.id = id.toUpperCase();
        this.hostel = hostel.toUpperCase();
        this.plan = Admin.PlansList.getPlan(planName);
        this.balance = plan.getRatePerCycle() * plan.getNoOfCycles();
        washCyclesLeft = plan.getNoOfCycles();
        Admin.updateRevenue(plan.getRatePerCycle()*plan.getNoOfCycles());
    }

    public String getName() { return name; }

    public String getId() { return id; }

    public String getHostel() { return hostel; }

    public String toString() {
        return
                "Name: " + name
                + ", ID: " + id
                + ", Hostel: " + hostel
                + ", Plan: " + plan.getName()
                + ", Semester Charge: " + balance;
    }

    public int getBalance() { return balance; }

    public int getAddCharge() { return this.addCharge; }

    // charged extra for extra wash used
    public void updateAddCharge(int extraCharge, Wash_Cycle cycle) {
        balance += extraCharge;
        addCharge += extraCharge;
        cycle.setAdditionalCharge(extraCharge);
        Admin.updateRevenue(extraCharge);
        Admin.StudentsList.updateStudents();
    }

    public void dropLaundry(float weight, Date date) {

        Wash_Cycle cycle = new Wash_Cycle(id, weight, date);
        totalWashes++;
        cycle.washId = id + ((totalWashes<=9)?"00":"0") + Integer.toString(totalWashes);
        washCyclesLeft--;
        int extraCharge = 0;
        if (washCyclesLeft < 0)
            extraCharge += (int)1.2*plan.getRatePerCycle();

        if(weight > plan.getWeight()) {
            // if weight limit exceeded, charge extra
            float extraWeight = weight - plan.getWeight();
            extraCharge += (int)(extraWeight * plan.getRatePerCycle()*1.2/plan.getWeight());
            System.out.println("You dropped " + extraWeight + " kg extra, so you are charged Rs" + extraCharge + " extra.");
        }
        updateAddCharge(extraCharge, cycle);

        System.out.print("Dropped the laundry succesfully!");
        cycle.scheduleDel();
        listOfWash_Cycles.add(cycle);

        if(Calendar.getInstance().get(Calendar.DAY_OF_WEEK) == 1 && Admin.weekNo == Admin.weeklyRecord.size() || Admin.weeklyRecord.size() == 0) {
            HashMap map = new HashMap<String, Integer>();
            map.put(id,1);
            Admin.weeklyRecord.add(map);
        }
        else {
            if(Admin.weeklyRecord.get(Admin.weekNo).get(id) == null) {
                Admin.weeklyRecord.get(Admin.weekNo).put(id, 1);
            }
            else {
                Admin.weeklyRecord.get(Admin.weekNo).replace(id, Admin.weeklyRecord.get(Admin.weekNo).get(id)+1);
            }
        }
        Admin.updateWeeklyRecord();

        Admin.StudentsList.updateStudents();
    }

    public void receiveLaundry(Wash_Cycle receivedCycle) {
        for(Wash_Cycle cycle:listOfWash_Cycles){
            if(cycle.equals(receivedCycle) && cycle.getWashStatus()){
                cycle.updateStatus("delivered");
            }
        }
        Admin.StudentsList.updateStudents();
        System.out.println(name + " received the laundry: " + receivedCycle.washId);
    }

    public void run() {
        synchronized (System.in) {
            try {
                Scanner sc = new Scanner(System.in);
                switch (func) {
                    case "D":
                        System.out.print("Enter the weight of laundry in kgs: ");
                        int weight = sc.nextInt();
                        System.out.print("Enter the day: ");
                        String day = sc.next().toUpperCase();
                        System.out.print("Enter the date in DD-MM-YYYY format: ");
                        try {
                            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(sc.next());
                            if (!HostelSchedule.hostelDropDay.get(this.hostel).equals(day)) {
                                System.out.println("You are not allowed to drop laundry on " + day + ". Please drop on your allotted day.");
                            } else {
                                this.dropLaundry(weight, date);
                            }
                        }
                        catch (Exception e) {}
                        break;

                    case "C":
                        if (this.listOfWash_Cycles.size() == 0 || this.listOfWash_Cycles.get(this.listOfWash_Cycles.size() - 1).isDelivered()) {
                            System.out.println("Laundry not yet dropped!");
                        } else if (!this.listOfWash_Cycles.get(this.listOfWash_Cycles.size() - 1).getWashStatus()) {
                            System.out.println("Washing in process...");
                        } else if (!this.listOfWash_Cycles.get(this.listOfWash_Cycles.size() - 1).getDryStatus()) {
                        System.out.println("Drying in process...");
                    } else {
                        if (this.plan.ironORfold())
                            System.out.println("Ironing in process...");
                        else
                            System.out.println("Folding in process...");
                        }
                        break;

                    case "B":
                        for (Wash_Cycle wash_cycle : this.listOfWash_Cycles) {
                            System.out.println(wash_cycle);
                        }
                        break;

                    case "R":
                        System.out.print("Enter Date when laundry was dropped: ");
                        try {
                            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(sc.next());
                        for (Wash_Cycle wash_cycle : this.listOfWash_Cycles) {
                            if(wash_cycle.placeDate.equals(date) && wash_cycle.isOnDelivery()) {
                                System.out.println("Laundry dropped on " + date + " is received.");
                                wash_cycle.updateStatus("delivered");
                            }
                            else if(wash_cycle.placeDate.equals(date)) {
                                System.out.println("Laundry dropped on " + date + " is not delivered yet.");
                            }
                        }
                        }
                        catch (Exception e) {}
                }
                System.in.notify();

            }
            catch (Exception e) {
                System.out.println(e);                            }
        }
    }

}