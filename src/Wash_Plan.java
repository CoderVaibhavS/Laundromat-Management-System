import java.io.Serializable;

interface Wash_Plan_Methods {
    public void updateRatePerCycle(int rate);
}

public class Wash_Plan implements Wash_Plan_Methods, Serializable {
    private String name;
    private int noOfCycles;
    private int ratePerCycle;
    private boolean iron;
    private String desc;

    Wash_Plan(String name, int noOfCycles, int ratePerCycle, boolean iron, String desc) {
        this.name = name;
        this.noOfCycles = noOfCycles;
        this.ratePerCycle = ratePerCycle;
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
        return "Plan Name: " + name + " , No. of cycles: " + noOfCycles + " , Rate Per Cycle: " + ratePerCycle + " , Plan description: " + desc;
    }

    public String getName() { return name; }
    public int getNoOfCycles() { return noOfCycles; }
    public int getRatePerCycle() { return ratePerCycle; }
    public String getDesc() { return desc; }
    public boolean ironORfold() { return iron; }

    public void updateRatePerCycle(int rate) { this.ratePerCycle = rate; }
}
