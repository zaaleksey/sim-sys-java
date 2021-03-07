package simsys.core.event;

import simsys.core.common.Factory;
import simsys.core.context.SimulationContext;
import simsys.core.event.handler.EventHandler;

public class HandledEventBuilderFactory implements Factory<HandledEvent.HandledEventBuilder> {

  private SimulationContext simulationContext;
  private EventHandler<HandledEvent> beforeHandler;
  private EventHandler<HandledEvent> afterHandler;


  public HandledEventBuilderFactory(
      SimulationContext simulationContext,
      EventHandler<HandledEvent> beforeHandler,
      EventHandler<HandledEvent> afterHandler) {
    this.simulationContext = simulationContext;
    this.afterHandler = afterHandler;
    this.beforeHandler = beforeHandler;
  }

  @Override
  public HandledEvent.HandledEventBuilder create() {
    HandledEvent.HandledEventBuilder builder = new HandledEvent.HandledEventBuilder(
        this.simulationContext);
    builder.addAfterHandler(this.afterHandler)
        .addBeforeHandler(this.beforeHandler);
    return builder;
  }

}
