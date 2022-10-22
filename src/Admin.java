import java.util.ArrayList;

public class Admin {
//    arraylist of all wash plans
    public static ArrayList<Wash_Plan> washPlans = new ArrayList<>();
//    list of all registered students
    public static ArrayList<Student> students = new ArrayList<>();

    static class PlansList {
        //    adds a new plan
        public static void addPlan(String name, int noOfCycles, int ratePerCycle, String desc) {
            Wash_Plan plan = new Wash_Plan(name, noOfCycles, ratePerCycle, desc);
            washPlans.add(plan);
        }

        //    removes a plan by matching its name from the arraylist of washplans
        public static void removePlan(String planName) {
            for(Wash_Plan plan: washPlans) {
                if (plan.getName().equals(planName)) {
                    washPlans.remove(plan);
                }
            }
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
        public static void addStudent(String name, String id, String hostel, String planName) {
            Student student = new Student(name, id, hostel, planName);
            students.add(student);
        }

//        removes a student from list on matching his/her id if the student opts out
        public static void removeStudent(String studentId) {
            for(Student student: students) {
                if (student.getId().equals(studentId)) {
                    students.remove(student);
                }
            }
        }
    }
}
