package simsys.entity.demand;

import simsys.core.parcel.Parcel;

public abstract class Demand implements Parcel {

  private static long counter = 0;

  protected long id;
  protected double creationTime;
  protected double arrivalTime;
  protected double serviceStartTime;
  protected double leavingTime;
  protected double networkTime;

  protected Demand() {
    counter++;
    id = counter;
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
    setNetworkTime(getLeavingTime() - getCreationTime());
  }

  public double getCreationTime() {
    return creationTime;
  }

  public void setCreationTime(double creationTime) {
    this.creationTime = creationTime;
  }

  public double getNetworkTime() {
    return networkTime;
  }

  public void setNetworkTime(double networkTime) {
    this.networkTime = networkTime;
  }

}
