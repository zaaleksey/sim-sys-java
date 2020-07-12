package simsys.entity.demand;

public abstract class Demand {
    private static long COUNTER = 0;

    protected long id;
    protected double creationTime;
    protected double arrivalTime;
    protected double serviceStartTime;
    protected double leavingTime;
    // The time from the arrival of the requirement to the network until its departure
    protected double networkTime;

    public Demand() {
        COUNTER++;
        id = COUNTER;
        this.serviceStartTime = Double.POSITIVE_INFINITY;
        this.leavingTime = Double.POSITIVE_INFINITY;
    }

    public long getId() {
        return id;
    }

    public double getArrivalTime() {
        return arrivalTime;
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
        // calculate the time spent in the network
        setNetworkTime(getLeavingTime() - getCreationTime());
    }

    public double getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(double creationTime) {
        this.creationTime = creationTime;
    }

    public double getNetworkTime() {
        return this.networkTime;
    }

    public void setNetworkTime(double networkTime) {
        this.networkTime = networkTime;
    }
}
