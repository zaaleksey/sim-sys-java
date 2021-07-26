package simsys.core.condition;

import java.util.function.Predicate;
import simsys.core.context.SimulationContext;

/**
 * The condition for stopping the simulation model when the simulation clock reaches a certain
 * value.
 */
public class TimeStopCondition implements Predicate<SimulationContext> {

  /**
   * The time when the model completes its simulation.
   */
  private final double simulationDuration;

  public TimeStopCondition(double simulationDuration) {
    this.simulationDuration = simulationDuration;
  }

  /**
   * Evaluates this predicate on the given argument. The condition for the current time of the
   * simulation model to reach the duration of the simulation.
   *
   * @param context the context of the simulation model
   * @return {@code true} if the current time has reached the simulation duration, otherwise {@code
   * false}
   */
  @Override
  public boolean test(SimulationContext context) {
    return context.getCurrentTime() > simulationDuration;
  }

}
