package simsys.api.containers;

import simsys.api.events.Event;

public interface EventContainer {

    int countEvents();
    boolean addEvent(Event event);
    boolean deleteUpcomingEvent();
    boolean isEmpty();
    Event getUpcomingEvent();
    Event getAndDeleteUpcomingEvent();
}
