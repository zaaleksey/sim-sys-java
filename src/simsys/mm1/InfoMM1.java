package simsys.mm1;

import simsys.api.random.RandomVariable;

public enum InfoMM1 {
    INFO;

    private double currentTime = 0;
    private double arrivalTime;
    private double averegeResponseTime;
    private int countOfDemands = 0;

    private RandomVariable arrivalRandom;
    private RandomVariable careRandom;

    public double getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(double currentTime) {
        this.currentTime = currentTime;
    }

    public double getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(double arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getCountOfDemands() {
        return countOfDemands;
    }

    public void increment() {
        this.countOfDemands++;
    }

    public double getAveregeResponseTime() {
        return averegeResponseTime;
    }

    public void setAveregeResponseTime(double averegeResponseTime) {
        this.averegeResponseTime = averegeResponseTime;
    }

    public double getNextArrivalValue() {
        return arrivalRandom.nextValue();
    }

    public void setArrivalRandom(RandomVariable arrivalRandom) {
        this.arrivalRandom = arrivalRandom;
    }

    public double getNextCareVariable() {
        return careRandom.nextValue();
    }

    public void setCareRandom(RandomVariable leaveVariable) {
        this.careRandom = leaveVariable;
    }
}
