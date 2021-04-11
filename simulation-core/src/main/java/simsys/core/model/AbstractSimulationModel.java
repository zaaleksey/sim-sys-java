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
    while (!this.stopCondition.test(this.simulationContext)) {
      step();
      System.out.println();

      System.out.println();
      List<Event> events = simulationContext.getEventProvider().getAllEvents();
      Collections.sort(events);
      for (Event event : events) {
        System.out.println(event.getClass().getName() + " act.time : " + event.getActivateTime());
      }
      System.out.println();
    }
  }


  @Override
  public void step() {
    Event event = this.simulationContext.getEventProvider().getNext();
    this.simulationContext.getClock().setCurrentTime(event.getActivateTime());
    this.simulationContext.updateDeltaTimeLastTwoEvents();
    event.activate();

    LOGGER.debug("The current time: " + this.simulationContext.getCurrentTime());
  }

  public Predicate<SimulationContext> getStopCondition() {
    return this.stopCondition;
  }

  public void setStopCondition(Predicate<SimulationContext> stopCondition) {
    this.stopCondition = stopCondition;
  }

}
