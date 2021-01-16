package simsys.system_components.api.demand;

public abstract class Demand {
    private static long COUNTER = 0;

    private long id;
    protected double arrivalTime;
    protected double serviceStartTime;
    protected double leavingTime;

    public Demand() {
        COUNTER++;
        id = COUNTER;
    }

    public double getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(double arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public long getID() {
        return id;
    }

    public double getServiceStartTime() {
        return serviceStartTime;
    }

    public void setServiceStartTime(double serviceStartTime) {
        this.serviceStartTime = serviceStartTime;
    }

    public double getLeavingTime() {
        return leavingTime;
    }

    public void setLeavingTime(double leavingTime) {
        this.leavingTime = leavingTime;
    }
}
