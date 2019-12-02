package simsys.api.events;

import java.util.Comparator;

public class DefaultEventComparator implements Comparator<Event> {

    @Override
    public int compare(Event event1, Event event2) {
        return event1.compareTo(event2);
    }
}
