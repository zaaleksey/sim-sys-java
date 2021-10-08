package simsys.core.event;

import lombok.SneakyThrows;
import simsys.core.exception.TimeErrorException;

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
  @SneakyThrows
  @Override
  public void setActivateTime(double activateTime) {
    if (activateTime < 0.0 || this.activateTime > activateTime) {
      throw new TimeErrorException(
          String.format("Incorrect time to activate the event:%s", activateTime));
    }
    this.activateTime = activateTime;
  }

}
