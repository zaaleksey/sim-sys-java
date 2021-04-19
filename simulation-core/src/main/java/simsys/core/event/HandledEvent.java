package simsys.core.event;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import simsys.core.context.SimulationContext;
import simsys.core.event.handler.EventHandler;
import simsys.core.event.handler.TimeoutHandler;
import simsys.core.exception.ImpossibleEventTime;
import simsys.random.RandomVariable;

public class HandledEvent extends AbstractEvent {

  protected ArrayList<EventHandler<HandledEvent>> handlers = new ArrayList<>();

  public HandledEvent addHandler(EventHandler<HandledEvent> eventHandler) {
    this.handlers.add(eventHandler);
    return this;
  }

  public List<EventHandler<HandledEvent>> getAllHandlers() {
    return this.handlers;
  }

  @Override
  public final void activate() {

    for (EventHandler<HandledEvent> handler : this.handlers) {
      try {
        handler.handle(this);
      } catch (ImpossibleEventTime | IllegalAccessException | NoSuchMethodException exception) {
        exception.printStackTrace();
      }
    }
  }

  public static class HandledEventBuilder {

    private final SimulationContext simulationContext;

    private final ArrayList<EventHandler<HandledEvent>> handlers;

    private final ArrayList<EventHandler<HandledEvent>> beforeHandlers;
    private final ArrayList<EventHandler<HandledEvent>> afterHandlers;

    private TimeoutHandler timeoutHandler;

    private Double startTime;

    public HandledEventBuilder(SimulationContext simulationContext) {
      this.simulationContext = simulationContext;
      this.handlers = new ArrayList<>();
      this.beforeHandlers = new ArrayList<>();
      this.afterHandlers = new ArrayList<>();
    }

    public HandledEventBuilder periodic(TimeoutHandler timeoutHandler) {
      this.timeoutHandler = timeoutHandler;
      return this;
    }

    public HandledEventBuilder periodic(RandomVariable randomVariable) {
      this.timeoutHandler = new TimeoutHandler(randomVariable);
      return this;
    }

    public HandledEventBuilder periodic(double interval) {
      this.timeoutHandler = new TimeoutHandler(interval);
      return this;
    }

    public HandledEventBuilder periodic(Supplier<Double> activateTimes) {
      this.timeoutHandler = new TimeoutHandler(activateTimes);
      return this;
    }

    public HandledEventBuilder addHandler(EventHandler<HandledEvent> eventHandler) {
      this.handlers.add(eventHandler);
      return this;
    }

    public HandledEventBuilder addBeforeHandler(EventHandler<HandledEvent> eventHandler) {
      this.beforeHandlers.add(eventHandler);
      return this;
    }

    public HandledEventBuilder addAfterHandler(EventHandler<HandledEvent> eventHandler) {
      this.afterHandlers.add(eventHandler);
      return this;
    }

    public HandledEventBuilder startTime(double time) {
      this.startTime = time;
      return this;
    }

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
