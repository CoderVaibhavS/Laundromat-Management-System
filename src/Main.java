import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
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
        StudentAuth.registerStudent("Vaibhav Singla", "1", "Vyas", "F_4","1234");
        StudentAuth.registerStudent("Rudra Goyal", "2021A7PS0708P", "Vyas", "F_4","5678");

        while(true) {
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
//             ALL ERROR CASES TO BE HANDLED
            switch (input) {
                // Student methods
                case "S":
                    System.out.print("Enter your id: ");
                    String id = sc.nextLine().toUpperCase();
                    System.out.print("Enter your name: ");
                    String name = sc.nextLine().toUpperCase();
                    System.out.print("Enter your password: ");
                    String password = sc.nextLine();
                    System.out.print("Enter your hostel name: ");
                    String hostel = sc.nextLine().toUpperCase();
                    System.out.println("Choose from the following washplans: ");
                    for (Wash_Plan plan: Admin.washPlans) {
                        System.out.println(plan);
                    }
                    System.out.print("Enter plan name: ");
                    String plan = sc.nextLine().toUpperCase();
                    StudentAuth.registerStudent(name, id, hostel, plan, password);
                    break;

                case "D":
                    System.out.print("Enter your id: ");
                    id = sc.next().toUpperCase();
                    Student student = StudentAuth.loginStudent(id);
                    if (student != null) {
                        student.t = new Thread(student);
                        student.func = "D";
                        student.t.start();
                        try {
                            student.t.join();
                        }
                        catch (Exception e){}

                    }
                    break;

                case "C":
                    System.out.print("Enter your id: ");
                    id = sc.next().toUpperCase();
                    student = StudentAuth.loginStudent(id);
                    if (student != null) {
                        student.t = new Thread(student);
                        student.func = "C";
                        student.t.start();
                        try {
                            student.t.join();
                        }
                        catch (Exception e){}
                    }
                    break;

                case "B":
                    System.out.print("Enter your id: ");
                    id = sc.next().toUpperCase();
                        student = StudentAuth.loginStudent(id);
                        if (student != null) {
                            student.t = new Thread(student);
                            student.func = "B";
                            student.t.start();
                            try {
                                student.t.join();
                            }
                            catch (Exception e){}
                        }
                    break;

                    case "R":
                        System.out.print("Enter your id: ");
                        id = sc.next().toUpperCase();
                        student = StudentAuth.loginStudent(id);
                        if (student != null) {
                            student.t = new Thread(student);
                            student.func = "R";
                            student.t.start();
                            try {
                                student.t.join();
                            }
                            catch (Exception e){}
                        }
                        break;

                // Admin methods
                case "L1":
                    Admin.t = new Thread();
                    Admin.t.start();
                    System.out.print("Enter Username: ");
                    String username = sc.next();
                    System.out.print("Enter Password: ");
                    password = sc.next();
                    if(username.equals("admin") && password.equals("admin")) {
                        Admin admin = new Admin(username, password);
                        System.out.println("Admin Login Successful!");
                    }
                    else
                        System.out.println("Incorrect username or password!");
                    break;

                case "AC":
                    try {
                        Admin.func = "AC";
                        if (Admin.isLoggedIn) {
                            Admin.t = new Thread(new Admin("admin", "admin"));
                            Admin.t.start();
                            Admin.t.join();
                        }else {
                            System.out.println("Need to log in first.");
                        }
                    }
                    catch (Exception e){
                        System.out.println(e);
                    }
                    break;

                case "T":
                    try {
                        Admin.func = "T";
                        if (Admin.isLoggedIn) {
                            Admin.t = new Thread(new Admin("admin", "admin"));
                            Admin.t.start();
                            Admin.t.join();
                        }else {
                            System.out.println("Need to log in first.");
                        }
                    }
                    catch (Exception e){
                        System.out.println(e);
                    }
                    break;

                case "U":
                    try {
                        Admin.func = "U";
                        if (Admin.isLoggedIn) {
                            Admin.t = new Thread(new Admin("admin", "admin"));
                            Admin.t.start();
                            Admin.t.join();
                        }else {
                            System.out.println("Need to log in first.");
                        }
                    }
                    catch (Exception e){
                        System.out.println(e);
                    }
                    break;

                case "SA":
                    try {
                        Admin.func = "SA";
                        if (Admin.isLoggedIn) {
                            Admin.t = new Thread(new Admin("admin", "admin"));
                            Admin.t.start();
                            Admin.t.join();
                        }else {
                            System.out.println("Need to log in first.");
                        }
                    }
                    catch (Exception e){
                        System.out.println(e);
                    }
                    break;

                case "RA":
                    try {
                        Admin.func = "RA";
                        if (Admin.isLoggedIn) {
                            Admin.t = new Thread(new Admin("admin", "admin"));
                            Admin.t.start();
                            Admin.t.join();
                        }else {
                            System.out.println("Need to log in first.");
                        }
                    }
                    catch (Exception e){
                        System.out.println(e);
                    }
                    break;

                case "L2":
                    if(Admin.isLoggedIn) {
                        Admin.isLoggedIn = false;
                        System.out.println("Logged out successfully!");
                    }
                    else
                        System.out.println("Need to log in first.");
                    break;
            }
        }

    }
}
