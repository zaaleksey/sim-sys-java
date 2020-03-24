package simsys.core.event;

public interface Event extends Comparable<Event> {

    double getActivateTime();

    void setActivateTime(double activateTime);

    default void postpone() {
        setActivateTime(Double.POSITIVE_INFINITY);
    }

    default double postpone(double duration) {
        setActivateTime(getActivateTime() + duration);
        return getActivateTime();
    }

    void activate();
}
