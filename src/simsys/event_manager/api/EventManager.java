package simsys.event_manager.api;

import simsys.event_sets.api.Event;

import java.util.List;

public interface EventManager {
    int countEvents();
    void addEvent(Event event);
    void addListEvent(List<Event> events);
    Event peekEvent();
    Event removeEvent();

    default boolean isEmpty() {
        return countEvents() == 0;
    }
}
