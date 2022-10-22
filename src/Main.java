public class Main {
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
        Admin.StudentsList.addStudent("Vaibhav Singla", "2021A7PS2227P", "Vyas", "F_4");
        System.out.println(Admin.students.get(0).getName());
    }
}