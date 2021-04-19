package simsys.core.event.handler;

import simsys.core.event.Event;

@FunctionalInterface
public interface EventHandler<T extends Event> {

  void handle(T event) throws IllegalAccessException, NoSuchMethodException;

}
