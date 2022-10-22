public class Wash_Plan {
    private final String name;
    private int noOfCycles;
    private int ratePerCycle;
    private final String desc;

    Wash_Plan(String name, int noOfCycles, int ratePerCycle, String desc) {
        this.name = name;
        this.noOfCycles = noOfCycles;
        this.ratePerCycle = ratePerCycle;
        this.desc = desc;
    }

    public String getName() { return name; }
    public int getNoOfCycles() { return noOfCycles; }
    public int getRatePerCycle() { return ratePerCycle; }
    public String getDesc() { return desc; }

    public void updateRatePerCycle(int rate) { this.ratePerCycle = rate; }
}
