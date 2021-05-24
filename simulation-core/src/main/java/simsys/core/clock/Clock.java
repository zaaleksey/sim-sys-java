package simsys.core.clock;

/**
 * Model time clock interface for the simulation model.
 */
public interface Clock {

  /**
   * Returns the time at which the previous event occurred. (for calculating the delta).
   *
   * @return the time of the previous event
   */
  double previousTime();

  /**
   * Returns the current time value of the simulation model.
   *
   * @return the current time value of the simulation model
   */
  double currentTime();

  /**
   * Sets the current time of the simulation model at a given time.
   *
   * @param currentTime the new time value of the simulation model
   */
  void setCurrentTime(double currentTime);

}
