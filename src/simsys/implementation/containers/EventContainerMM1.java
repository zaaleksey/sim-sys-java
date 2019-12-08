package simsys.implementation.containers;

import simsys.api.containers.EventContainer;
import simsys.api.events.Event;

import java.util.Comparator;
import java.util.TreeSet;

public class EventContainerMM1 implements EventContainer {

    private TreeSet<Event> container;

    public EventContainerMM1(Comparator<Event> comparator) {
        container = new TreeSet<>(comparator);
    }

    @Override
    public int countEvents() {
        return container.size();
    }

    @Override
    public boolean addEvent(Event event) {
        return container.add(event);
    }

    @Override
    public boolean deleteUpcomingEvent() {
        return container.remove(container.first());
    }

    @Override
    public boolean isEmpty() {
        return container.isEmpty();
    }

    @Override
    public Event getUpcomingEvent() {
        return container.first();
    }

    @Override
    public Event getAndDeleteUpcomingEvent() {
        return container.pollFirst();
    }

    @Override
    public String toString() {
        return container.toString();
    }
}
