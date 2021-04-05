package simsys.core.exception;

public class ImpossibleEventTime extends RuntimeException {

  public ImpossibleEventTime(double eventActionTime, double currentTime) {
    super("Unable to add the event with activation time"
        + eventActionTime + "because currentTime: " + currentTime);
  }

}
