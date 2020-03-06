package simsys.implementation.containers;

import simsys.api.containers.EventManager;
import simsys.api.events.Event;

import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

public class EventManagerMM1 implements EventManager {
    private TreeSet<Event> container;

    public EventManagerMM1(Comparator<Event> comparator) {
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
    public boolean addListEvent(List<Event> events) {
        return container.addAll(events);
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
