package simsys.core.provider;

import simsys.core.event.Event;
import simsys.core.event.HandledEvent;

import java.util.Collection;

public interface EventProvider {
    int count();

    void add(HandledEvent event);

    void addAll(Collection<HandledEvent> events);

    Event peek();

    Event getNext();

    Event remove();

    default boolean isEmpty() {
        return count() == 0;
    }
}
