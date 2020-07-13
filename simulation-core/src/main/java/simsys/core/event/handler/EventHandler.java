package simsys.core.event.handler;

import simsys.core.event.Event;


/**
 * @param <T>
 */
@FunctionalInterface
public interface EventHandler<T extends Event> {

  void handle(T event);
}
