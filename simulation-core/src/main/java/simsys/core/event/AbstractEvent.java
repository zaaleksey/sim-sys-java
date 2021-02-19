package simsys.core.event;

public abstract class AbstractEvent implements Event {

  protected double activateTime;

  @Override
  public double getActivateTime() {
    return activateTime;
  }

  @Override
  public void setActivateTime(double activateTime) {
    this.activateTime = activateTime;
  }

}
