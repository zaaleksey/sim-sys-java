package simsys.core.provider;

import java.util.Collection;
import java.util.List;
import simsys.core.event.Event;

/**
 * Provider interface for storing events in order of occurrence by activation time.
 */
public interface EventProvider {

  /**
   * Returns the number of events in the provider.
   *
   * @return the number of events in the provider
   */
  int count();

  /**
   * Adding an event to the provider.
   *
   * @param event the event to be placed in the provider
   */
  void add(Event event);

  /**
   * Adding a collection of events to the provider.
   *
   * @param events a collection of events to be placed in the provider
   */
  void addAll(Collection<Event> events);

  /**
   * Returns an event with the closest activation time without removing the event from the
   * provider.
   *
   * @return an event with the closest activation time
   */
  Event peek();

  /**
   * Returns an event with the closest activation time, removing the event from the provider.
   *
   * @return an event with the closest activation time
   */
  Event getNext();

  /**
   * Returns a list of all events in the provider.
   *
   * @return a list of events
   */
  List<Event> getAllEvents();

  /**
   * Removing an event from a provider.
   *
   * @param event event to be deleted
   * @return {@code true} if this provider contained an event passed as a parameter, otherwise
   * {@code false}
   */
  boolean remove(Event event);

  /**
   * Checking the provider for emptiness.
   *
   * @return {@code true} if there are no events in this provider (it is empty), otherwise {@code
   * false}
   */
  default boolean isEmpty() {
    return count() == 0;
  }

}
