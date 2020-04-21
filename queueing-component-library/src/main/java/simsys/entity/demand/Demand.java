package simsys.entity.demand;

public abstract class Demand {
    private static long COUNTER = 0;

    //А если у нас будет сеть обслуживания, то как будем хранить время прохождения через всю сеть?
    protected long id;
    protected double creationTime;
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

    public double getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(double creationTime) {
        this.creationTime = creationTime;
    }
}
