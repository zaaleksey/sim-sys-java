package simsys.core.event;

import simsys.core.context.SimulationContext;
import simsys.core.event.handler.EventHandler;
import simsys.core.event.handler.TimeoutHandler;
import simsys.core.exception.ImpossibleEventTime;
import simsys.random.RandomVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * Implementing an abstract event. A ready event has handlers that will sequentially process this event.
 */
public class HandledEvent extends AbstractEvent {

  /**
   * List of handlers for this event.
   */
  protected ArrayList<EventHandler<HandledEvent>> handlers = new ArrayList<>();

  /**
   * Adds a handler to this event.
   *
   * @param eventHandler event handler
   * @return this event
   */
  public HandledEvent addHandler(EventHandler<HandledEvent> eventHandler) {
    handlers.add(eventHandler);
    return this;
  }

  /**
   * Returns a list of all handlers for this event.
   *
   * @return a list of all handlers for this event
   */
  public List<EventHandler<HandledEvent>> getAllHandlers() {
    return handlers;
  }

  /**
   * Event activation method. Describes the logic for handling the event.
   * Sequential call of all handlers of this event.
   */
  @Override
  public final void activate() {

    for (EventHandler<HandledEvent> handler : this.handlers) {
      try {
        handler.handle(this);
      } catch (ImpossibleEventTime exception) {
        exception.printStackTrace();
      }
    }
  }

  /**
   * Builder for constructing handled events.
   */
  public static class HandledEventBuilder {

    /**
     * The context of the simulation model with the objects necessary for simulation.
     */
    private final SimulationContext simulationContext;

    /**
     * List of handlers for this event.
     */
    private final ArrayList<EventHandler<HandledEvent>> handlers;

    /**
     * List of handlers that will be activated before the event itself is activated.
     */
    private final ArrayList<EventHandler<HandledEvent>> beforeHandlers;
    /**
     * List of handlers that will be activated after the activation of the event itself.
     */
    private final ArrayList<EventHandler<HandledEvent>> afterHandlers;

    /**
     * Timeout handler for the frequency of event activation.
     */
    private TimeoutHandler timeoutHandler;

    /**
     * Time of the very first activation of the event.
     */
    private Double startTime;

    public HandledEventBuilder(SimulationContext simulationContext) {
      this.simulationContext = simulationContext;
      this.handlers = new ArrayList<>();
      this.beforeHandlers = new ArrayList<>();
      this.afterHandlers = new ArrayList<>();
    }

    /**
     * Sets a timeout handler.
     *
     * @param timeoutHandler a timeout handler
     * @return this builder
     */
    public HandledEventBuilder periodic(TimeoutHandler timeoutHandler) {
      this.timeoutHandler = timeoutHandler;
      return this;
    }

    /**
     * Sets a timeout with a random interval value handle.
     *
     * @param randomVariable random value generator
     * @return this builder
     */
    public HandledEventBuilder periodic(RandomVariable randomVariable) {
      this.timeoutHandler = new TimeoutHandler(randomVariable);
      return this;
    }

    /**
     * Sets a timeout at a constant interval.
     *
     * @param interval interval value
     * @return this builder
     */
    public HandledEventBuilder periodic(double interval) {
      this.timeoutHandler = new TimeoutHandler(interval);
      return this;
    }

    /**
     * Sets a timeout with the transmitted supplier.
     *
     * @param activateTimes supplier deciding when the event will be activated again
     * @return this builder
     */
    public HandledEventBuilder periodic(Supplier<Double> activateTimes) {
      this.timeoutHandler = new TimeoutHandler(activateTimes);
      return this;
    }

    /**
     * Adds a handler to the event.
     *
     * @param eventHandler event handler
     * @return this builder
     */
    public HandledEventBuilder addHandler(EventHandler<HandledEvent> eventHandler) {
      this.handlers.add(eventHandler);
      return this;
    }

    /**
     * Adds handlers that will be executed before the event is activated.
     *
     * @param eventHandler event handler
     * @return this builder
     */
    public HandledEventBuilder addBeforeHandler(EventHandler<HandledEvent> eventHandler) {
      this.beforeHandlers.add(eventHandler);
      return this;
    }

    /**
     * Adds handlers that will be executed after the event is activated.
     *
     * @param eventHandler event handler
     * @return this builder
     */
    public HandledEventBuilder addAfterHandler(EventHandler<HandledEvent> eventHandler) {
      this.afterHandlers.add(eventHandler);
      return this;
    }

    /**
     * Sets the time of the very first activation of the event.
     *
     * @param time start time
     * @return this builder
     */
    public HandledEventBuilder startTime(double time) {
      this.startTime = time;
      return this;
    }

    /**
     * Builds a handled event. Configures parameters and handlers for this event.
     * Returns a constructed event.
     *
     * @return a handled event
     */
    public HandledEvent build() {
      HandledEvent handledEvent = new HandledEvent();

      handledEvent.handlers.addAll(this.beforeHandlers);
      handledEvent.handlers.addAll(this.handlers);

      if (this.timeoutHandler != null) {
        timeoutHandler.setSimulationContext(simulationContext);
        handledEvent.handlers.add(timeoutHandler);
      }

      handledEvent.handlers.addAll(this.afterHandlers);

      if (this.startTime != null) {
        handledEvent.setActivateTime(this.startTime);
      }
      return handledEvent;
    }

  }

}
