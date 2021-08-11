package simsys.core.event.handler;

import java.util.function.Supplier;
import simsys.core.context.SimulationContext;
import simsys.core.event.Event;
import simsys.random.RandomVariable;

/**
 * Timeout handler. Handles the event at certain intervals defined by some policy.
 */
public class TimeoutHandler implements EventHandler {

  /**
   * The context of the simulation model with the objects necessary for simulation.
   */
  protected SimulationContext simulationContext;

  /**
   * Supplier deciding when the event will be activated again.
   */
  private Supplier<Double> activateTimes;

  /**
   * Timeout with constant interval between handling.
   *
   * @param interval handle interval
   */
  public TimeoutHandler(double interval) {
    this.activateTimes = new Supplier<Double>() {
      double nextTime = 0;

      @Override
      public Double get() {
        nextTime += interval;
        return nextTime;
      }
    };
  }

  /**
   * Timeout with a random value of the interval between handling.
   *
   * @param randomVariable random value generator
   */
  public TimeoutHandler(RandomVariable randomVariable) {
    this.activateTimes = new Supplier<Double>() {
      double nextTime = 0;

      @Override
      public Double get() {
        nextTime += randomVariable.nextValue();
        return nextTime;
      }
    };
  }

  /**
   * @param activateTimes activate times
   */
  public TimeoutHandler(Supplier<Double> activateTimes) {
    this.activateTimes = activateTimes;
  }

  /**
   * Sets the context of the simulation model.
   *
   * @param simulationContext the context of the simulation model
   */
  public void setSimulationContext(SimulationContext simulationContext) {
    this.simulationContext = simulationContext;
  }

  /**
   * Handles the event. Sets the event to the next activation time and puts it back into the
   * provider. The next activation time is determined by some policy (after a certain time interval
   * or a random time interval, the value of which obeys a certain distribution law).
   *
   * @param event the event to be handled
   */
  @Override
  public void handle(Event event) {
    event.setActivateTime(activateTimes.get());
    simulationContext.getEventProvider().add(event);
  }

}
