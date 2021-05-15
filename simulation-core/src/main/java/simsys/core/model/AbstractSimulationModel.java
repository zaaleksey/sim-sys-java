package simsys.core.model;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import lombok.extern.slf4j.Slf4j;
import simsys.core.context.SimulationContext;
import simsys.core.event.Event;

@Slf4j
public abstract class AbstractSimulationModel implements SimulationModel {

  protected SimulationContext simulationContext;
  protected Predicate<SimulationContext> stopCondition;

  @Override
  public void run() {
    while (!this.stopCondition.test(simulationContext)) {
      step();

      // TODO: make optional
      List<Event> events = simulationContext.getEventProvider().getAllEvents();
      Collections.sort(events);
      for (Event event : events) {
        LOGGER.debug("{} act.time : {}", event.getClass().getName(), event.getActivateTime());
      }
    }
  }

  @Override
  public void step() {
    // TODO: здесь вызываем из simulation context observers
    //  и считаем статистику - тут знаем время между последовательными событиями

    Event event = simulationContext.getEventProvider().getNext();
    simulationContext.getClock().setCurrentTime(event.getActivateTime());
    simulationContext.updateDeltaTimeLastTwoEvents();
    event.activate();

    LOGGER.debug("STEP: The current time: {} \n",
        simulationContext.getCurrentTime());
  }

  public Predicate<SimulationContext> getStopCondition() {
    return this.stopCondition;
  }

  public void setStopCondition(Predicate<SimulationContext> stopCondition) {
    this.stopCondition = stopCondition;
  }

  public void setSimulationContext(SimulationContext simulationContext) {
    this.simulationContext = simulationContext;
  }

}
