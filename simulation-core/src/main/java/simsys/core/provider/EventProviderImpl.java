package simsys.core.provider;

import simsys.core.event.Event;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


/**
 * Basic implementation of the EventProvider interface for storing events, via an ArrayList.
 */
public class EventProviderImpl implements EventProvider {

  /**
   * List in which events will be placed.
   */
  private final List<Event> events;


  public EventProviderImpl(Collection<? extends Event> events) {
    this.events = new ArrayList<>();
    this.events.addAll(events);
  }

  /** Returns the number of events in the provider.
   *
   * @return the number of events in the provider
   */
  @Override
  public int count() {
    return this.events.size();
  }

  /**
   * Adding an event to the provider.
   *
   * @param event the event to be placed in the provider
   */
  @Override
  public void add(Event event) {
    this.events.add(event);
  }

  /**
   * Adding a collection of events to the provider.
   *
   * @param events a collection of events to be placed in the provider
   */
  @Override
  public void addAll(Collection<Event> events) {
    this.events.addAll(events);
  }

  /** Returns an event with the closest activation time without removing the event from the provider.
   *  To do this, use the min method of the Collections interface.
   *
   * @return an event with the closest activation time
   */
  @Override
  public Event peek() {
    return Collections.min(this.events);
  }

  /**
   * Returns an event with the closest activation time, removing the event from the provider.
   *
   * @return an event with the closest activation time
   * @see #peek() 
   * @see #remove(Event)
   */
  @Override
  public Event getNext() {
    Event nextEvent = peek();
    remove(nextEvent);
    return nextEvent;
  }

  /**
   * Returns a list of all events in the provider.
   *
   * @return a list of events
   */
  @Override
  public List<Event> getAllEvents() {
    return events;
  }

  /**
   * Removing an event from a provider.
   *
   * @param event event to be deleted
   * @return {@code true} if this provider contained an event passed as a parameter,
   * otherwise {@code false}
   */
  @Override
  public boolean remove(Event event) {
    return events.remove(event);
  }

}
