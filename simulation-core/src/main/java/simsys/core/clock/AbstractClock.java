package simsys.core.clock;

import lombok.SneakyThrows;
import simsys.core.exception.TimeErrorException;

/**
 * Basic abstract implementation of the Clock interface.
 */
public abstract class AbstractClock implements Clock {

  /**
   * The current time value of the simulation model.
   */
  protected double currentTime;

  /**
   * Returns the current time value of the simulation model.
   *
   * @return the current time value of the simulation model
   */
  @Override
  public double currentTime() {
    return currentTime;
  }

  /**
   * Sets the current time of the simulation model at a given time.
   *
   * @param currentTime the new time value of the simulation model
   */
  @SneakyThrows
  @Override
  public void setCurrentTime(double currentTime) {
    if (currentTime < 0.0 || this.currentTime > currentTime) {
      throw new TimeErrorException(
          String.format("Unable to set the clock. Incorrect time:%s", currentTime));
    }

    this.currentTime = currentTime;
  }

}
