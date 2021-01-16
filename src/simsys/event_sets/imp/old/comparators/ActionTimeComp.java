package simsys.event_sets.imp.old.comparators;

import simsys.event_sets.api.Event;

import java.util.Comparator;

public class ActionTimeComp implements Comparator<Event> {

    @Override
    public int compare(Event event1, Event event2) {
        return event1.compareTo(event2);
    }
}
