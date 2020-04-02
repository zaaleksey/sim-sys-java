package simsys.core.provider;

import simsys.core.event.Event;

import java.util.Collection;

public interface EventProvider {
    int count();

    void add(Event event);

    void addAll(Collection<Event> events);

    Event peek();

    Event getNext();

    boolean remove(Event event);

    default boolean isEmpty() {
        return count() == 0;
    }
}
