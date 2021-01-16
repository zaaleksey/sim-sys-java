package simsys.event_sets.imp.old.comparators;

import simsys.event_sets.api.Event;
import simsys.event_sets.imp.old.events.CareDemandsEvent;

import java.util.Comparator;

public class ActionTimeAndPriorityComp implements Comparator<Event> {
    @Override
    public int compare(Event event1, Event event2) {
        if (event1.compareTo(event2) == 0) {
            if (event1 instanceof CareDemandsEvent) {
                return -1;
            }
        }
        return event1.compareTo(event2);
    }
}
