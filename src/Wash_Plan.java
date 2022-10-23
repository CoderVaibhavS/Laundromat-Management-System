interface Wash_Plan_Methods {
    public void updateRatePerCycle(int rate);
}

public class Wash_Plan implements Wash_Plan_Methods {
    private String name;
    private int noOfCycles;
    private int ratePerCycle;
    private String desc;

    Wash_Plan(String name, int noOfCycles, int ratePerCycle, String desc) {
        this.name = name;
        this.noOfCycles = noOfCycles;
        this.ratePerCycle = ratePerCycle;
        this.desc = desc;
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

    public String getName() { return name; }
    public int getNoOfCycles() { return noOfCycles; }
    public int getRatePerCycle() { return ratePerCycle; }
    public String getDesc() { return desc; }

    public void updateRatePerCycle(int rate) { this.ratePerCycle = rate; }
}
