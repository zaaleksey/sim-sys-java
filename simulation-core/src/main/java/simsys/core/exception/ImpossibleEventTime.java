package simsys.core.exception;

/**
 * This exception is thrown when adding an event to the provider with an incorrect activation time.
 */
public class ImpossibleEventTime extends RuntimeException {

  public ImpossibleEventTime(double eventActionTime, double currentTime) {
    super("Unable to add the event with activation time"
        + eventActionTime + "because currentTime: " + currentTime);
  }

}
