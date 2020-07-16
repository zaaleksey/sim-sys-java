package simsys.core.provider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import simsys.core.event.Event;
import simsys.core.exception.ImpossibleEventTime;

public class EventProviderImpl implements EventProvider {

  private ArrayList<Event> events;

  public EventProviderImpl(Collection<? extends Event> events) {
    this.events = new ArrayList<>();
    this.events.addAll(events);
  }

  @Override
  public int count() {
    return events.size();
  }


  @Override
  public void add(Event event, double currentTime) throws ImpossibleEventTime {
    if (event.getActivateTime() < currentTime) {
      throw new ImpossibleEventTime(event.getActivateTime(), currentTime);
    }
    events.add(event);
  }

  @Override
  public void addAll(Collection<Event> events, double currentTime) throws ImpossibleEventTime {
    for (Event event : events) {
      if (event.getActivateTime() < currentTime) {
        throw new ImpossibleEventTime(event.getActivateTime(), currentTime);
      }
    }
    this.events.addAll(events);
  }

  @Override
  public Event peek() {
    return Collections.min(events);
  }

  @Override
  public Event getNext() {
    Event nextEvent = peek();
    remove(nextEvent);
    return nextEvent;
  }

  @Override
  public boolean remove(Event event) {
    return events.remove(event);
  }
}
