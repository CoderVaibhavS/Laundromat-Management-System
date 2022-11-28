import java.io.*;

public class StudentAuth implements Serializable {
    public static String message;

    public static void registerStudent(String name, String id, String hostel, String planName, String password) throws IOException{

        File f = new File("students.txt");
        try {
            f.createNewFile();
        }
        catch (Exception e) {
        }

        // check if the student is already registered
        if(Admin.students.get(id) != null) {
            System.out.println("User with id: " + id + " already exists!");
        }

        // register the new student
        else {
            Student newStudent = new Student(name, id, hostel, planName, password);

            try {
                FileOutputStream fos = null;
                fos = new FileOutputStream("students.txt", true);
                if (f.length() == 0) {
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(newStudent);
                    oos.close();
                } else {
                    MyObjectOutputStream oos = null;
                    oos = new MyObjectOutputStream(fos);
                    oos.writeObject(newStudent);
                    oos.close();
                }
                System.out.println("User with id: " + id + " successfully registered!");
                fos.close();
            } catch (Exception e) {
                System.out.println("Exception occurred: " + e);
            }

            Admin.StudentsList.addStudent(newStudent);
        }
    }
    public static Student loginStudent(String id){
        if(Admin.students.get(id) == null){
            message = "User with " + id + " does not exist!";
        }
//        else if(Admin.students.get(id).password.equals(password)){
//            message = "Login Successful!";
//            System.out.println(message);
//            return Admin.students.get(id);
//            }
//        else{
//            message = "Incorrect Password!";
//        }
        else {
            return Admin.students.get(id);
        }

        System.out.println(message);
        return null;
    }

    public static Student logoutStudent() {
        message = "Logged Out!";
        System.out.println(message);
        return null;
    }
}