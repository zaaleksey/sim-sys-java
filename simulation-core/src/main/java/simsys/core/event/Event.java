package simsys.core.event;

import java.util.Comparator;

public interface Event extends Comparator<Event> {

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

    @Override
    default int compare(Event event1, Event event2) {
        return Double.compare(event1.getActivateTime(), event2.getActivateTime());
    }
}
