import java.io.Serializable;


public class Wash_Plan implements Serializable {
    private String name;
    private int noOfCycles;
    private int ratePerCycle;
    private float weight;
    private boolean iron;
    private String desc;

    Wash_Plan(String name, int noOfCycles, int ratePerCycle, float weight, boolean iron, String desc) {
        this.name = name;
        this.noOfCycles = noOfCycles;
        this.ratePerCycle = ratePerCycle;
        this.weight = weight;
        this.desc = desc;
        this.iron = iron;
    }

    Wash_Plan(String name) {
        for(Wash_Plan plan: Admin.washPlans) {
            if(plan.getName().equals(name)) {
                this.noOfCycles = plan.getNoOfCycles();
                this.ratePerCycle = plan.getRatePerCycle();
                this.desc = plan.getDesc();
            }
        }
    }

    public String toString() {
        return "Plan Name: " + name + " , No. of cycles: " + noOfCycles + " , Rate Per Cycle: " + ratePerCycle + " , Weight per Cycle: " + weight + " , Plan description: " + desc;
    }

    public String getName() { return name; }
    public int getNoOfCycles() { return noOfCycles; }
    public int getRatePerCycle() { return ratePerCycle; }
    public String getDesc() { return desc; }
    public boolean ironORfold() { return iron; }
    public float getWeight() { return weight; }
}
