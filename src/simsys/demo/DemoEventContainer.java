package simsys.demo;

import simsys.containers.EventContainer;
import simsys.events.Event;

import java.util.ArrayList;
import java.util.List;

public class DemoEventContainer implements EventContainer {

    private List<Event> container = new ArrayList<>();

    @Override
    public int countEvents() {
        return container.size();
    }

    @Override
    public boolean addEvent(Object o) {
        if (o instanceof Event){
            Event event = (Event) o;
            container.add(event);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteEvent() {
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
    public Event getAndDeleteFirstEvent() {
        Event event = getFirstEvent();
        deleteEvent();
        return event;
    }
}
