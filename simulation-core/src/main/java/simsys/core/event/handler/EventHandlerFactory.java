package simsys.core.event.handler;

import simsys.core.common.Factory;

public interface EventHandlerFactory extends Factory<EventHandler<?>> {

  @Override
  EventHandler<?> create();

}
