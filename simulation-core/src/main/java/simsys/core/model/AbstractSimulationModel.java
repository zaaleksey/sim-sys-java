package simsys.core.model;

import java.util.ArrayList;
import java.util.Collections;
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
    while (!this.stopCondition.test(this.simulationContext)) {
      step();
      LOGGER.debug("\n");
      showAllScheduledEvents();
    }
  }

  private void showAllScheduledEvents() {
    System.out.println();
    ArrayList<Event> scheduledEvents = (ArrayList<Event>) this.simulationContext
        .getEventProvider().getAllEvents();
    Collections.sort(scheduledEvents);

    System.out.println("Current time:" + this.simulationContext.getCurrentTime());
    for (Event scheduledEvent : scheduledEvents) {
      System.out.println("Event:" + scheduledEvent.getClass() + " --- \taction time: "
          + scheduledEvent.getActivateTime());
    }
    System.out.println();

  }

  @Override
  public void step() {
    Event event = this.simulationContext.getEventProvider().getNext();
    this.simulationContext.getClock().setCurrentTime(event.getActivateTime());
    LOGGER.debug("The current time: " + this.simulationContext.getCurrentTime());
    event.activate();

    this.simulationContext.updateDeltaTimeLastTwoEvents();
  }

  public Predicate<SimulationContext> getStopCondition() {
    return this.stopCondition;
  }

  public void setStopCondition(Predicate<SimulationContext> stopCondition) {
    this.stopCondition = stopCondition;
  }

}
