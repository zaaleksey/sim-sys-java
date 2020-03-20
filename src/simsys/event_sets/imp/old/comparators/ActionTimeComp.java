package old.implementation.events.comparators;

import old.api.events.Event;
import java.util.Comparator;

public class ActionTimeComp implements Comparator<Event> {

    @Override
    public int compare(Event event1, Event event2) {
        return event1.compareTo(event2);
    }
}
