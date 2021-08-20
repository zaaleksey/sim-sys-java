package simsys.core.event;

import simsys.core.common.Factory;
import simsys.core.context.SimulationContext;
import simsys.core.event.handler.EventHandler;

/**
 * Factory interface for creating handled event builder.
 */
public class HandledEventBuilderFactory implements Factory<HandledEvent.HandledEventBuilder> {

  /**
   * The context of the simulation model with the objects necessary for simulation.
   */
  private SimulationContext simulationContext;
  /**
   * Handler that will be activated before the event itself is activated.
   */
  private EventHandler<HandledEvent> beforeHandler;
  /**
   * Handler that will be activated after the event itself is activated.
   */
  private EventHandler<HandledEvent> afterHandler;


  /**
   * @param simulationContext simulation context
   * @param beforeHandler before handler
   * @param afterHandler after handler
   */
  public HandledEventBuilderFactory(
      SimulationContext simulationContext,
      EventHandler<HandledEvent> beforeHandler,
      EventHandler<HandledEvent> afterHandler) {
    this.simulationContext = simulationContext;
    this.afterHandler = afterHandler;
    this.beforeHandler = beforeHandler;
  }

  /**
   * Method for creating a handled event builder. Adds preprocessing and postprocessing to an
   * builder.
   *
   * @return handled event builder
   */
  @Override
  public HandledEvent.HandledEventBuilder create() {
    HandledEvent.HandledEventBuilder builder = new HandledEvent.HandledEventBuilder(
        simulationContext);
    builder
        .addAfterHandler(afterHandler)
        .addBeforeHandler(beforeHandler);
    return builder;
  }

}
