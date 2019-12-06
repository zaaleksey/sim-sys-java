package simsys.implementation.containers;

import simsys.api.containers.EventContainer;
import simsys.api.events.Event;

import java.util.ArrayDeque;
import java.util.logging.Logger;

public class QueueMM1 implements EventContainer {

    private Logger logger = Logger.getLogger(QueueMM1.class.getName());

    private ArrayDeque<Event> queueMM1;

    public QueueMM1()
    {
        queueMM1 = new ArrayDeque<>();
    }

    @Override
    public int countEvents() {
        return queueMM1.size();
    }

    @Override
    public boolean addEvent(Event event) {
        return queueMM1.add(event);
    }

    @Override
    public boolean deleteUpcomingEvent() {
        if (isEmpty()) {
            return false;
        }
        getAndDeleteUpcomingEvent();
        return true;
    }

    @Override
    public boolean isEmpty() {
        return queueMM1.isEmpty();
    }

    @Override
    public Event getUpcomingEvent() {
        return queueMM1.getFirst();
    }

    @Override
    public Event getAndDeleteUpcomingEvent() {
        if (isEmpty()) {
            logger.warning("M/M/1. The queue is empty!");
            return null;
        }
        return queueMM1.remove();
    }
}
