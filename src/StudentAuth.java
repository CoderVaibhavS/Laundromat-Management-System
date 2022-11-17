public class StudentAuth{
    public static String message;

    public static void registerStudent(String name, String id, String hostel, String planName, String password){
        if(Admin.students.get(id) != null) {
            message = "User with id: " + id + " already exists!";
            System.out.println(message);
            return;
        }
        Student newStudent = new Student(name,id,hostel,planName,password);
        Admin.StudentsList.addStudent(newStudent);
    }
    public static Student loginStudent(String id, String password){
        if(Admin.students.get(id) == null){
            message = "User does not exist!";
        }
        else if(Admin.students.get(id).password.equals(password)){
            message = "Login Successful!";
            System.out.println(message);
            return Admin.students.get(id);
            }
        else{
            message = "Incorrect Password!";
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