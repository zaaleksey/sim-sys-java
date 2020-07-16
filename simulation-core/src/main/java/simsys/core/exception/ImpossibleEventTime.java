package simsys.core.exception;

public class ImpossibleEventTime extends Exception {

    public ImpossibleEventTime(double eventActionTime, double currentTime) {
        super("Unable to add event with activation time" +
                eventActionTime + "at the current time" + currentTime);
    }
}
