import java.util.*;

public class Main {
    public static void delay(int n){
        try {
            System.out.println("Delay for "+n+" seconds");
            Thread.sleep(n*1000); //delay for n seconds(wash time during testing)
        } 
        catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }
    }
    public static void main(String[] args) {
//        adding wash plans to admin's plans list
        Admin.PlansList.addPlan("F_4", 4, 185, false, "Wash + Dry + Fold");
        Admin.PlansList.addPlan("I_4", 4, 245, true, "Wash + Dry + Iron");
        Admin.PlansList.addPlan("F_8", 8, 165, false, "Wash + Dry + Fold");
        Admin.PlansList.addPlan("I_8", 8, 220, true, "Wash + Dry + Iron");
        Admin.PlansList.addPlan("F_10", 10, 155, false, "Wash + Dry + Fold");
        Admin.PlansList.addPlan("I_10", 10, 200, true, "Wash + Dry + Iron");
        Admin.PlansList.addPlan("F_15", 15, 150, false, "Wash + Dry + Fold");
        Admin.PlansList.addPlan("I_15", 15, 196, true, "Wash + Dry + Iron");

        // ONLY FOR TESTING
        StudentAuth.registerStudent("Vaibhav Singla", "2021A7PS2227P", "Vyas", "F_4","1234");
        Student vaibhav = StudentAuth.loginStudent("2021A7PS2227P");
        vaibhav.dropLaundry(3);


        while(true) {
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();

            // ALL ERROR CASES TO BE HANDLED
            switch (input) {
                case "S":
                    System.out.print("Enter your id: ");
                    String id = sc.nextLine();
                    System.out.print("Enter your name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter your password: ");
                    String password = sc.nextLine();
                    System.out.print("Enter your hostel name: ");
                    String hostel = sc.nextLine();
                    System.out.println("Choose from the following washplans: ");
                    for (Wash_Plan plan: Admin.washPlans) {
                        System.out.println(plan);
                    }
                    System.out.print("Enter plan name: ");
                    String plan = sc.nextLine();
                    StudentAuth.registerStudent(name, id, hostel, plan, password);
                    break;

                case "D":
                    System.out.print("Enter your id: ");
                    id = sc.next();
                    System.out.print("Enter the weight of laundry in kgs: ");
                    int weight = sc.nextInt();
                    System.out.print("Enter the date in DD/MM/YYYY format: ");
                    Date d = new Date();
                    String date = sc.next();
                    System.out.print("Enter the day: ");
                    String day = sc.next();
                    Student student = StudentAuth.loginStudent(id);
                    if (!HostelDelTime.hostelDropDay.get(student.hostel).equals(day)) {
                        System.out.println("You are not allowed to drop laundry on " + day + ". Please drop on your allotted day.");
                    }
                    else {
                        student.dropLaundry(weight);
                    }
                    break;

                case "C":
                    System.out.print("Enter your id: ");
                    id = sc.next();
                    student = StudentAuth.loginStudent(id);
                    if(student.listOfWash_Cycles.size() == 0 || student.listOfWash_Cycles.get(student.listOfWash_Cycles.size() - 1).received) {
                        System.out.println("Laundry not yet dropped!");
                    }
                    else if (!student.listOfWash_Cycles.get(student.listOfWash_Cycles.size() - 1).washStatus) {
                        System.out.println("Washing in process...");
                    }
                    else if (!student.listOfWash_Cycles.get(student.listOfWash_Cycles.size() - 1).dryStatus) {
                        System.out.println("Drying in process...");
                    }
                    else {
                        if (student.plan.ironORfold())
                            System.out.println("Ironing in process...");
                        else
                            System.out.println("Folding in process...");
                    }
                    break;
            }
        }

//        student added to admin's student list after registering
//        StudentAuth.registerStudent("Vaibhav Singla", "2021A7PS2227P", "Vyas", "F_4","1234");
//        StudentAuth.registerStudent("Rudra Goyal", "2021A7PS0708P", "Vyas", "F_4","5678");
//        StudentAuth.registerStudent("Vaibhav Singla", "2021A7PS2227P", "Vyas", "F_4","1234");
//        System.out.println(Admin.weeklyRecord);
//
//        StudentAuth.logoutStudent();
//        Student vaibhav = StudentAuth.loginStudent("2021A7PS2227P", "1234");
//        Student rudra = Admin.students.get("2021A7PS0708P");
//
//        vaibhav.dropLaundry(5);
//        vaibhav.refreshCycles();
//        System.out.println(vaibhav.listOfWash_Cycles.get(0).status);
//        delay(4);
//        System.out.println(vaibhav.listOfWash_Cycles.get(0).status);
//        vaibhav.refreshCycles();
//        System.out.println(vaibhav.listOfWash_Cycles.get(0).status);
//        vaibhav.receiveLaundry(vaibhav.listOfWash_Cycles.get(0));
//
//        rudra.dropLaundry(5);
//        rudra.dropLaundry(10);
//        delay(5);
//        rudra.receiveLaundry(rudra.listOfWash_Cycles.get(1));
//        System.out.println(rudra.totalWashes);
//        System.out.println(rudra.plan.getRatePerCycle()*rudra.plan.getNoOfCycles() + " + " + rudra.getAddCharge() + " = " + rudra.getBalance());
//        System.out.println(Admin.weeklyRecord);
    }
}
