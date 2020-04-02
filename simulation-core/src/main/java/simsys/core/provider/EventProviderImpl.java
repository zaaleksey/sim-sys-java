package simsys.core.provider;

import simsys.core.event.Event;
import simsys.core.event.HandledEvent;

import java.util.Collection;

public class EventProviderImpl implements EventProvider {
    Collection<HandledEvent> events;

    public EventProviderImpl(Collection<HandledEvent> events) {
        this.events = events;
    }

    @Override
    public int count() {
        return events.size();
    }

    @Override
    public void add(HandledEvent event) {
        events.add(event);
    }

    @Override
    public void addAll(Collection<HandledEvent> events) {
        this.events.addAll(events);
    }

    @Override
    public Event peek() {
        return events.iterator().next();
    }

    @Override
    public Event getNext() {
        Event nextEvent = peek();
        remove(nextEvent);
        return nextEvent;
    }

    @Override
    public boolean remove(Event event) {
        return events.remove(event instanceof HandledEvent);
    }
}
