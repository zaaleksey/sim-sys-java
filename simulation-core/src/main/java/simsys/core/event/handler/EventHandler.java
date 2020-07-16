package simsys.core.event.handler;

import simsys.core.event.Event;
import simsys.core.exception.ImpossibleEventTime;


/**
 * @param <T>
 */
@FunctionalInterface
public interface EventHandler<T extends Event> {

  void handle(T event) throws ImpossibleEventTime;
}
