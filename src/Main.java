import java.io.*;
import java.util.*;


public class Main{
    public static void main(String[] args) throws IOException {
        boolean running =true;
//        adding wash plans to admin's plans list
        Admin.PlansList.addPlan("F_4", 4, 185, 6, false, "Wash + Dry + Fold");
        Admin.PlansList.addPlan("I_4", 4, 245, 6, true, "Wash + Dry + Iron");
        Admin.PlansList.addPlan("F_8", 8, 165, 6, false, "Wash + Dry + Fold");
        Admin.PlansList.addPlan("I_8", 8, 220, 6, true, "Wash + Dry + Iron");
        Admin.PlansList.addPlan("F_10", 10, 155, 6, false, "Wash + Dry + Fold");
        Admin.PlansList.addPlan("I_10", 10, 200, 6, true, "Wash + Dry + Iron");
        Admin.PlansList.addPlan("F_15", 15, 150, 6, false, "Wash + Dry + Fold");
        Admin.PlansList.addPlan("I_15", 15, 196, 6, true, "Wash + Dry + Iron");

        File f = new File("students.txt");
        try {
            f.createNewFile();
        }
        catch (Exception e) {
        }

        if (f.length() != 0) {
            try {
                FileInputStream fis = null;
                fis = new FileInputStream("students.txt");
                ObjectInputStream ois = new ObjectInputStream(fis);

                Student student = null;

                while (fis.available() != 0) {
                    student = (Student) ois.readObject();
                    Admin.StudentsList.addStudent(student);
                    Admin.updateRevenue(student.getBalance());
                }
                ois.close();
                fis.close();
            }
            catch (Exception e) {
                System.out.println("Error Occurred" + e);
                e.printStackTrace();
            }
        }

        f = new File("weeklyrecord.txt");
        try {
            f.createNewFile();
        }
        catch (Exception e) {
        }

        if (f.length() != 0) {
            try {
                FileInputStream fis = null;
                fis = new FileInputStream("weeklyrecord.txt");
                ObjectInputStream ois = new ObjectInputStream(fis);

                HashMap<String, Integer> map = null;

                while (fis.available() != 0) {
                    map = (HashMap<String, Integer>) ois.readObject();
                    Admin.weeklyRecord.add(map);
                }
                ois.close();
                fis.close();
            }
            catch (Exception e) {
                System.out.println("Error Occurred" + e);
                e.printStackTrace();
            }
        }

        // ONLY FOR TESTING
        StudentAuth.registerStudent("Vaibhav Singla", "2021A7PS2227P", "Vyas", "F_4","1234");
        StudentAuth.registerStudent("Rudra Goyal", "2021A7PS0708P", "Vyas", "F_4","5678");

//        Student vaibhav = Admin.students.get("2021A7PS2227P");
//        vaibhav.dropLaundry(5);
//        vaibhav.dropLaundry(10);
//        System.out.println(vaibhav.totalWashes);
//        for (HashMap<String,Integer> map: Admin.weeklyRecord) {
//            System.out.println(map.entrySet());
//        }
        HomePageGUI hp = new HomePageGUI();
        String id,password;
//         while(running) {
//             String input;
//             Scanner sc = new Scanner(System.in);
//             // String input = sc.nextLine();
           
//             input = hp.getInput();
//             System.out.println("MAIN" + input);
// //             ALL ERROR CASES TO BE HANDLED
//             switch (input) {
//                 case "S":
//                     // System.out.print("Enter your id: ");
//                     // String id = sc.nextLine().toUpperCase();
//                     // System.out.print("Enter your name: ");
//                     // String name = sc.nextLine().toUpperCase();
//                     // System.out.print("Enter your password: ");
//                     // String password = sc.nextLine();
//                     // System.out.print("Enter your hostel name: ");
//                     // String hostel = sc.nextLine().toUpperCase();
//                     // System.out.println("Choose from the following washplans: ");
//                     // for (Wash_Plan plan: Admin.washPlans) {
//                     //     System.out.println(plan);
//                     // }
//                     // System.out.print("Enter plan name: ");
//                     // String plan = sc.nextLine().toUpperCase();
//                     // StudentAuth.registerStudent(name, id, hostel, plan, password);
//                     RegisterGUI r = new RegisterGUI();
//                     break;

//                 case "D":
//                     System.out.print("Enter your id: ");
//                     id = sc.next().toUpperCase();
//                     Student student = StudentAuth.loginStudent(id);
//                     if (student != null) {
//                         student.t = new Thread(student);
//                         student.func = "D";
//                         student.t.start();
//                     }
//                     break;

                // case "C":
                //     System.out.print("Enter your id: ");
                //     id = sc.next().toUpperCase();
                //     student = StudentAuth.loginStudent(id);
                //     if(student.listOfWash_Cycles.size() == 0 || student.listOfWash_Cycles.get(student.listOfWash_Cycles.size() - 1).received) {
                //         System.out.println("Laundry not yet dropped!");
                //     }
                //     else if (!student.listOfWash_Cycles.get(student.listOfWash_Cycles.size() - 1).getWashStatus()) {
                //         System.out.println("Washing in process...");
                //     }
                //     else if (!student.listOfWash_Cycles.get(student.listOfWash_Cycles.size() - 1).getDryStatus()) {
                //         System.out.println("Drying in process...");
                //     }
                //     else {
                //         if (student.plan.ironORfold())
                //             System.out.println("Ironing in process...");
                //         else
                //             System.out.println("Folding in process...");
                //     }
                //     break;

//                 case "AC":
//                     System.out.print("Enter Username: ");
//                     String username = sc.next();
//                     System.out.print("Enter Password: ");
//                     password = sc.next();
//                     if(username.equals("admin") && password.equals("admin")) {
//                         Admin admin = new Admin(username, password);
//                         System.out.println("Admin Login Successful!");
//                     }
//                     else
//                         System.out.println("Incorrect username or password!");
//                     break;

//                 case "L2":
//                     if(Admin.isLoggedIn) {
//                         Admin.isLoggedIn = false;
//                         System.out.println("Logged out successfully!");
//                     }
//                     else
//                         System.out.println("Need to log in first.");
//                     break;
//             }
//             sc.close();
//         }
    }
}
