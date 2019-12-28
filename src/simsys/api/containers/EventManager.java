package simsys.api.containers;

import simsys.api.events.Event;

import java.util.List;

public interface EventManager {

    int countEvents();
    boolean addEvent(Event event);
    boolean addListEvent(List<Event> events);
    boolean deleteUpcomingEvent();
    boolean isEmpty();
    Event getUpcomingEvent();
    Event getAndDeleteUpcomingEvent();

}
