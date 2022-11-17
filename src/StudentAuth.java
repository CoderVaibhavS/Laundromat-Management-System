public class StudentAuth{
    public String message;

    public static void registerStudent(String name, String id, String hostel, String planName, String password){
        Student newStudent = new Student(name,id,hostel,planName,password);
        Admin.StudentsList.addStudent(newStudent);
    }
    public Student loginStudent(String id, String password){
        if(Admin.students.get(id) ==null){
            message = "User does not exist";
            return null;
        }
        else if(Admin.students.get(id).password != password){
            message = "Incorrect Password";
            return null;
        }
        else if(Admin.students.get(id).password == password){
            message = "Login Successful";
            return Admin.students.get(id);
        }
        else{
            message = "unknown error";
            return null;
        }

    }
}