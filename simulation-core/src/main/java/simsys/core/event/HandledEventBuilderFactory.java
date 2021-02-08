package simsys.core.event;

import simsys.core.common.Factory;
import simsys.core.context.SimulationContext;
import simsys.core.event.handler.EventHandler;

public class HandledEventBuilderFactory implements Factory<HandledEvent.HandledEventBuilder> {

  private SimulationContext simulationContext;
  private EventHandler beforeHandler;
  private EventHandler afterHandler;


  public HandledEventBuilderFactory(SimulationContext simulationContext,
      EventHandler beforeHandler, EventHandler afterHandler) {
    this.simulationContext = simulationContext;
    this.afterHandler = afterHandler;
    this.beforeHandler = beforeHandler;
  }

  @Override
  public HandledEvent.HandledEventBuilder create() {
    HandledEvent.HandledEventBuilder builder = new HandledEvent.HandledEventBuilder(
        simulationContext);
    builder.addAfterHandler(afterHandler)
        .addBeforeHandler(beforeHandler);
    return builder;
  }

}
