package simsys.core.event.handler;

import simsys.core.event.Event;

public interface EventHandler<T extends Event> {
    void handle(T event);

    void addNext(EventHandler<?> next);

    EventHandler<?> getNext();
}
