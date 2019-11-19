package simsys.containers;

public interface EventContainer {

    int countEvents();
    boolean addEvent(Object event);
    boolean deleteEvent();
    boolean isEmpty();
    Object getFirstEvent();
    Object getAndDeleteFirstEvent();
}
