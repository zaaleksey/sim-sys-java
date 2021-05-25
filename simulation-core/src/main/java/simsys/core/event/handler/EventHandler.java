package simsys.core.event.handler;

import simsys.core.event.Event;

/**
 * Functional interface for event handler. Serves to handle events (before/after)
 *
 * @param <T> event type
 */
@FunctionalInterface
public interface EventHandler<T extends Event> {

  /**
   * The method containing the logic for handling the event.
   *
   * @param event the event to be handled
   */
  void handle(T event);

}
