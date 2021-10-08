package simsys.core.exception;

/**
 * This exception is thrown when adding an event to the provider with an incorrect activation time.
 */
public class ImpossibleEventTimeException extends RuntimeException {

  /**
   * @param eventActionTime event action time
   * @param currentTime current time
   */
  public ImpossibleEventTimeException(double eventActionTime, double currentTime) {
    super("Unable to add the event with activation time"
        + eventActionTime + "because currentTime: " + currentTime);
  }

}
