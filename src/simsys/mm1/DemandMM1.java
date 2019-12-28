package simsys.mm1;

public class DemandMM1 {

    private static long count = 0;

    public long ID;
    private double arrivingTime;
    private double serviceStartTime;
    private double serviceCompletionTime;

    public DemandMM1(double arrivingTime) {
        ID = ++count;
        this.arrivingTime = arrivingTime;
        this.serviceStartTime = Double.POSITIVE_INFINITY;
        this.serviceCompletionTime =Double.POSITIVE_INFINITY;
    }

    public DemandMM1(double arrivingTime, double serviceStartTime, double serviceCompletionTime) {
        ID = ++count;
        this.arrivingTime = arrivingTime;
        this.serviceStartTime = serviceStartTime;
        this.serviceCompletionTime = serviceCompletionTime;
    }

    public double getArrivingTime() {
        return arrivingTime;
    }

    public void setArrivingTime(double arrivingTime) {
        this.arrivingTime = arrivingTime;
    }

    public double getServiceStartTime() {
        return serviceStartTime;
    }

    public void setServiceStartTime(double serviceStartTime) {
        this.serviceStartTime = serviceStartTime;
    }

    public double getServiceCompletionTime() {
        return serviceCompletionTime;
    }

    public void setServiceCompletionTime(double serviceCompletionTime) {
        this.serviceCompletionTime = serviceCompletionTime;
    }
}
