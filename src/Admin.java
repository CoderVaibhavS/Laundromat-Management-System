import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

public class Admin extends User implements Runnable {

    public static boolean isLoggedIn;
    Admin(String username, String password) {
        super(username, password);
        isLoggedIn = true;
    }

    private static int revenue = 0;
//    arraylist of all wash plans
    public static ArrayList<Wash_Plan> washPlans = new ArrayList<>();
//    list of all registered students
    public static Map<String,Student> students = new HashMap<>();
//    weekly tracking
    public static ArrayList<HashMap<String, Integer>> weeklyRecord = new ArrayList<>();
    public static void updateRevenue(int amount) { revenue += amount; }
    public static int getRevenue() { return revenue; }
    static int weekNo = weeklyRecord.size();
    static Thread t;
    static String func;

    public static synchronized void updateWeeklyRecord() {
        File f = new File("weeklyrecord.txt");
        f.delete();
        try {
            f.createNewFile();
        }
        catch (Exception e) {
        }

        for(HashMap<String, Integer> map: weeklyRecord) {
            try {
                FileOutputStream fos = null;
                fos = new FileOutputStream("weeklyrecord.txt", true);
                if (f.length() == 0) {
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(map);
                    oos.close();
                } else {
                    MyObjectOutputStream oos = null;
                    oos = new MyObjectOutputStream(fos);
                    oos.writeObject(map);
                    oos.close();
                }
                fos.close();
            } catch (Exception e) {
                System.out.println("Exception occurred: " + e);
            }
        }

    }

    static class PlansList {
        //    adds a new plan
        public static void addPlan(String name, int noOfCycles, int ratePerCycle, float weight, boolean iron, String desc) {
            Wash_Plan plan = new Wash_Plan(name, noOfCycles, ratePerCycle, weight, iron, desc);
            washPlans.add(plan);
        }

        //    gets the whole plan object from its name
        public static Wash_Plan getPlan(String planName) {
            for(Wash_Plan plan: washPlans) {
                if (plan.getName().equals(planName)) {
                    return plan;
                }
            }
            return null;
        }
    }

    static class StudentsList {
//        adds a new student after registering
        public static void addStudent(Student student){
            students.put(student.id, student);
        }

        public static synchronized void updateStudents() {
            File f = new File("students.txt");
            f.delete();
            try {
                f.createNewFile();
            }
            catch (Exception e) {
            }

            Iterator<Student> itr = students.values().iterator();
            while (itr.hasNext()) {
                try {
                    FileOutputStream fos = null;
                    fos = new FileOutputStream("students.txt", true);
                    if (f.length() == 0) {
                        ObjectOutputStream oos = new ObjectOutputStream(fos);
                        oos.writeObject(itr.next());
                        oos.close();
                    } else {
                        MyObjectOutputStream oos = null;
                        oos = new MyObjectOutputStream(fos);
                        oos.writeObject(itr.next());
                        oos.close();
                    }
                    fos.close();
                } catch (Exception e) {
//                    System.out.println("Exception occurred: " + e);
                }
            }
        }
    }

    public void run() {
        synchronized (System.in) {
            Scanner sc = new Scanner(System.in);
            switch (func) {
                case "AC":
                    Iterator<Student> itr = Admin.students.values().iterator();
                    while (itr.hasNext()) {
                        System.out.println(itr.next());
                    }
                    break;

                case "T":
                    System.out.print("Enter id: ");
                    String id = sc.next().toUpperCase();
                    Student student = Admin.students.get(id);
                    System.out.print("Enter Date when laundry was dropped: ");
                    LocalDate date = LocalDate.parse(sc.next());
                    if(student == null) {
                        System.out.println("User with id " + id + " doesn't exist." );
                        break;
                    }
                    for (Wash_Cycle wash_cycle : student.listOfWash_Cycles) {
                        if(wash_cycle.placeDate.equals(date)) {
                            if (wash_cycle.isOnDelivery()) {
                                System.out.println("Laundry on delivery.");
                            }
                            else if(wash_cycle.getIronOrFoldStatus()) {
                                if(student.plan.ironORfold()) System.out.println("Ironing in process...");
                                else System.out.println("Folding in process...");
                            }
                            else if(wash_cycle.getDryStatus()) {
                                System.out.println("Drying in process...");
                            }
                            else {
                                System.out.println("Washing in process...");
                            }
                        }
                        else {
                            System.out.println("Laundry not dropped on " + date + " by " + student.getId() + ".");
                        }
                    }
                    break;

                case "U":
                    System.out.print("Enter id: ");
                    id = sc.next().toUpperCase();
                    student = Admin.students.get(id);
                    System.out.print("Enter Date when laundry was dropped: ");
                    date = LocalDate.parse(sc.next());
                    System.out.print("Update status: ");
                    String status = sc.next().toUpperCase();
                    if(student == null) {
                        System.out.println("User with id " + id + " doesn't exist." );
                        break;
                    }
                    for (Wash_Cycle wash_cycle : student.listOfWash_Cycles) {
                        if(wash_cycle.placeDate.equals(date)) {
                            switch (status) {
                                case "WASH":
                                case "DRY":
                                case "F_OR_I":
                                case "ONDELIVERY":
                                    wash_cycle.updateStatus(status);
                                    break;
                                default:
                                    System.out.println("Invalid status!");
                            }
                        }
                        else {
                            System.out.println("Laundry not dropped on " + date + " by " + student.getId() + ".");
                        }
                    }
                    break;
            }
        }
    }
}
