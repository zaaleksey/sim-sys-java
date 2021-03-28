package simsys.core.provider;

import java.util.Collection;
import java.util.List;
import simsys.core.event.Event;

public interface EventProvider {

  int count();

  void add(Event event);

  void addAll(Collection<Event> events);

  Event peek();

  Event getNext();

  List<Event> getAllEvents();

  boolean remove(Event event);

  default boolean isEmpty() {
    return count() == 0;
  }

}
