package simsys.implementation.containers;

import simsys.api.containers.EventContainer;
import simsys.api.events.Event;

import java.util.ArrayList;
import java.util.List;

public class DemoEventContainer implements EventContainer {

    private List<Event> container = new ArrayList<>();

    @Override
    public int countEvents() {
        return container.size();
    }

    @Override
    public boolean addEvent(Event event) {
        container.add(event);
        return true;
    }

    @Override
    public boolean deleteUpcomingEvent() {
        if (countEvents() == 0)
            return false;
        container.remove(0);
        return true;
    }

    @Override
    public boolean isEmpty() {
        return countEvents() == 0;
    }

    @Override
    public Event getFirstEvent() {
        return container.get(0);
    }

    @Override
    public Event getAndDeleteUpcomingEvent() {
        Event event = getFirstEvent();
        deleteUpcomingEvent();
        return event;
    }
}
