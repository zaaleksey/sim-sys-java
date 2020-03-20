package old.implementation.events.comparators;

import old.api.events.Event;
import old.implementation.events.CareDemandsEvent;

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
