package simsys.core.model;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import lombok.extern.slf4j.Slf4j;
import me.tongfei.progressbar.ProgressBar;
import simsys.core.condition.TimeStopCondition;
import simsys.core.context.SimulationContext;
import simsys.core.event.Event;

/**
 * Abstract interface implementation {@code SimulationModel}. Implements the default {@code run} and
 * {@code step} methods of the simulation model.
 */
@Slf4j
public abstract class AbstractSimulationModel implements SimulationModel {

  /**
   * Model simulation duration.
   */
  private final double simulationDuration;
  /**
   * Flag that determines the need for logging.
   */
  private boolean log = false;
  /**
   * The context of the simulation model with the objects necessary for simulation.
   */
  protected SimulationContext simulationContext;

  /**
   * Predicate with the condition of stopping the simulation cycle.
   */
  protected Predicate<SimulationContext> stopCondition;

  protected AbstractSimulationModel(double simulationDuration) {
    this.simulationDuration = simulationDuration;

    stopCondition = new TimeStopCondition(simulationDuration);
  }

  /**
   * The {@code step} method is executed until the stop condition is reached.
   */
  @Override
  public void run() {
    try (ProgressBar progressBar = new ProgressBar("Simulation:", (long) simulationDuration)) {
      while (!stopCondition.test(simulationContext)) {
        step();
        progressBar.stepTo((long) simulationContext.getCurrentTime());

        if (log) {
          logStep();
        }
      }
    }
  }

  /**
   * Logging the step of the simulation model. Poor performance due to constant sorting of the event
   * collection.
   */
  private void logStep() {
    List<Event> events = simulationContext.getEventProvider().getAllEvents();
    Collections.sort(events);
    for (Event event : events) {
      LOGGER.debug("{} act.time : {}", event.getClass().getName(), event.getActivateTime());
    }
  }

  /**
   * An event with the nearest activation time is received from the provider. This event is being
   * processed. The variables responsible for the simulation time are updated.
   */
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

  /**
   * Sets the condition for stopping the simulation.
   *
   * @param stopCondition the condition for stopping the simulation
   */
  public void defineStopCondition(Predicate<SimulationContext> stopCondition) {
    this.stopCondition = stopCondition;
  }

  /**
   * Sets the context of the simulation model.
   *
   * @param simulationContext the context of the simulation model
   */
  public void setSimulationContext(SimulationContext simulationContext) {
    this.simulationContext = simulationContext;
  }

  public void setLog(boolean log) {
    this.log = log;
  }
}
