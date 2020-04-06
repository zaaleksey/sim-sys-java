package simsys.entity.demand;

import simsys.entity.Entity;

public abstract class Demand implements Entity {
    private static long COUNTER = 0;

    protected long id;
    protected double arrivalTime;
    protected double serviceStartTime;
    protected double leavingTime;

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
    }
}
