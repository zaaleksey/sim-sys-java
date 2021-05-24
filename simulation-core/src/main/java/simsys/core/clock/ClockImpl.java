package simsys.core.clock;

import org.springframework.stereotype.Component;

/**
 * Basic implementation of the Clock interface.
 */
@Component
public final class ClockImpl implements Clock {

  /**
   * The time at which the previous event occurred.
   */
  private double previousTime;
  /**
   * The current time value of the simulation model.
   */
  private double currentTime;

  public ClockImpl() {
    this.previousTime = 0;
    this.currentTime = 0;
  }

  /**
   * Returns the time at which the previous event occurred. (for calculating the delta).
   *
   * @return the time of the previous event
   */
  @Override
  public double previousTime() {
    return this.previousTime;
  }

  /**
   * Returns the current time value of the simulation model.
   *
   * @return the current time value of the simulation model
   */
  @Override
  public double currentTime() {
    return this.currentTime;
  }

  /**
   * Sets the current time of the simulation model at a given time.
   *
   * @param currentTime the new time value of the simulation model
   */
  @Override
  public void setCurrentTime(double currentTime) {
    this.previousTime = this.currentTime;
    this.currentTime = currentTime;
  }

}
