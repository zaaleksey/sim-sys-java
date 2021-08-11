package simsys.core.clock;

import org.springframework.stereotype.Component;

/**
 * A clock that remembers the time interval between the last two events.
 */
@Component
public final class MemoryClock extends AbstractClock {

  /**
   * The time at which the previous event occurred.
   */
  private double previousTime;

  /**
   *
   */
  public MemoryClock() {
    this.previousTime = 0;
    this.currentTime = 0;
  }

  /**
   * Sets the current time of the simulation model at a given time. Also remembers the time of the
   * previous activation of the event.
   *
   * @param currentTime the new time value of the simulation model
   */
  @Override
  public void setCurrentTime(double currentTime) {
    previousTime = this.currentTime;
    super.setCurrentTime(currentTime);
  }

  /**
   * Returns the time delta.
   *
   * @return the time delta
   */
  @Override
  public double deltaTime() {
    return currentTime - previousTime;
  }

}
