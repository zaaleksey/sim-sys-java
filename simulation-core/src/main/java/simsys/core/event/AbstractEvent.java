package simsys.core.event;

/**
 * An abstract class that implements the Event interface.
 */
public abstract class AbstractEvent implements Event {

  /**
   * The activation time of this event.
   */
  protected double activateTime;

  /**
   * Returns the activation time of this event.
   *
   * @return the activation time of this event
   */
  @Override
  public double getActivateTime() {
    return activateTime;
  }

  /**
   * Sets the activation time for this event.
   *
   * @param activateTime the event activation time
   */
  @Override
  public void setActivateTime(double activateTime) {
    this.activateTime = activateTime;
  }

}
