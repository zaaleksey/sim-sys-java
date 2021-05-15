package simsys.core.clock;

/**
 * Time clock object interface in the simulation model.
 */
public interface Clock {

  /**
   * Provides the time of the preceding event (for calculating the delta).
   *
   * @return time of the previous event
   */
  double getPreviousTime();

  /**
   * Provides the current time of the simulation model.
   *
   * @return current time
   */
  double getCurrentTime();

  /**
   * Sets the current time of the simulation model at a given time.
   *
   * @param currentTime - new current time in the model
   */
  void setCurrentTime(double currentTime);

}
