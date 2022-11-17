
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
        Admin.PlansList.addPlan("F_4", 4, 185, "Wash + Dry + Fold");
        Admin.PlansList.addPlan("I_4", 4, 245, "Wash + Dry + Iron");
        Admin.PlansList.addPlan("F_8", 8, 165, "Wash + Dry + Fold");
        Admin.PlansList.addPlan("I_8", 8, 220, "Wash + Dry + Iron");
        Admin.PlansList.addPlan("F_10", 10, 155, "Wash + Dry + Fold");
        Admin.PlansList.addPlan("I_10", 10, 200, "Wash + Dry + Iron");
        Admin.PlansList.addPlan("F_15", 15, 150, "Wash + Dry + Fold");
        Admin.PlansList.addPlan("I_15", 15, 196, "Wash + Dry + Iron");

//        student added to admin's student list after registering
        // Admin.StudentsList.addStudent("Vaibhav Singla", "2021A7PS2227P", "Vyas", "F_4");
        // Admin.StudentsList.addStudent("Rudra Goyal", "2021A7PS0708P", "Gandhi", "I_4");
        StudentAuth.registerStudent("Vaibhav Singla", "2021A7PS2227P", "Vyas", "F_4","1234");
        StudentAuth.registerStudent("Rudra Goyal", "2021A7PS0708P", "Vyas", "F_4","5678");
        StudentAuth.registerStudent("Vaibhav Singla", "2021A7PS2227P", "Vyas", "F_4","1234");


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
    }
}
