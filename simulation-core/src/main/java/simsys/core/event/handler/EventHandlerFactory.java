package simsys.core.event.handler;

import simsys.core.common.Factory;

/**
 * Factory for creating event handlers.
 */
public interface EventHandlerFactory extends Factory<EventHandler<?>> {

  /**
   * Method for creating event handlers.
   *
   * @return event handler
   */
  @Override
  EventHandler<?> create();

}
