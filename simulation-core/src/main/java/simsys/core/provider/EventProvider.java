package simsys.core.provider;

import java.util.Collection;
import simsys.core.event.Event;
import simsys.core.exception.ImpossibleEventTime;

public interface EventProvider {

  int count();

  void add(Event event, double currentTime) throws ImpossibleEventTime;

  void addAll(Collection<Event> events, double currentTime) throws ImpossibleEventTime;

  Event peek();

  Event getNext();

  boolean remove(Event event);

  default boolean isEmpty() {
    return count() == 0;
  }
}
