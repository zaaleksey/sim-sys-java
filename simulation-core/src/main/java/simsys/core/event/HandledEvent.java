package simsys.core.event;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import lombok.SneakyThrows;
import simsys.core.context.SimulationContext;
import simsys.core.event.handler.EventHandler;
import simsys.core.event.handler.TimeoutHandler;
import simsys.core.exception.ImpossibleEventTime;
import simsys.random.RandomVariable;


public class HandledEvent extends AbstractEvent {

  protected ArrayList<EventHandler> handlers = new ArrayList<>();

  public void addHandler(EventHandler<HandledEvent> eventHandler) {
    handlers.add(eventHandler);
  }

  public List<EventHandler> getAllHandlers() {
    return handlers;
  }

  @SneakyThrows
  @Override
  public final void activate() {
    for (EventHandler handler : handlers) {
      try {
        handler.handle(this);
      } catch (ImpossibleEventTime impossibleEventTime) {
        impossibleEventTime.printStackTrace();
      }
    }
  }


  public static class HandledEventBuilder {

    private final SimulationContext simulationContext;

    private final ArrayList<EventHandler> handlers;

    private final ArrayList<EventHandler> beforeHandlers;
    private final ArrayList<EventHandler> afterHandlers;

    private TimeoutHandler timeoutHandler;

    private Double startTime;

    public HandledEventBuilder(SimulationContext simulationContext) {
      this.simulationContext = simulationContext;
      handlers = new ArrayList<>();
      beforeHandlers = new ArrayList<>();
      afterHandlers = new ArrayList<>();
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


    public HandledEventBuilder addHandler(EventHandler eventHandler) {
      handlers.add(eventHandler);
      return this;
    }

    public HandledEventBuilder addBeforeHandler(EventHandler eventHandler) {
      beforeHandlers.add(eventHandler);
      return this;
    }

    public HandledEventBuilder addAfterHandler(EventHandler eventHandler) {
      afterHandlers.add(eventHandler);
      return this;
    }

    public HandledEventBuilder startTime(double time) {
      this.startTime = time;
      return this;
    }


    public HandledEvent build() {
      HandledEvent handledEvent = new HandledEvent();

      handledEvent.handlers.addAll(beforeHandlers);
      handledEvent.handlers.addAll(handlers);

      if (timeoutHandler != null) {
        timeoutHandler.setSimulationContext(simulationContext);
        handledEvent.handlers.add(timeoutHandler);
      }

      handledEvent.handlers.addAll(afterHandlers);

      if (this.startTime != null) {
        handledEvent.setActivateTime(startTime);
      }
      return handledEvent;
    }

  }
}
