import java.util.ArrayList;

public class Admin {
    private static int revenue;
//    arraylist of all wash plans
    public static ArrayList<Wash_Plan> washPlans = new ArrayList<>();
//    list of all registered students
    public static ArrayList<StudentAccount> students = new ArrayList<>();
    public static int getRevenue() { return revenue; }

    static class PlansList {
        //    adds a new plan
        public static void addPlan(String name, int noOfCycles, int ratePerCycle, String desc) {
            Wash_Plan plan = new Wash_Plan(name, noOfCycles, ratePerCycle, desc);
            washPlans.add(plan);
        }

        //    removes a plan by matching its name from the arraylist of wash plans
        public static void removePlan(String planName) {
            washPlans.removeIf(plan -> plan.getName().equals(planName));
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
            StudentAccount student = new StudentAccount(name, id, hostel, planName);
            students.add(student);
        }

//        removes a student from list on matching his/her id if the student opts out
        public static void removeStudent(String studentId) {
            students.removeIf(student -> student.getId().equals(studentId));
        }
    }
}
