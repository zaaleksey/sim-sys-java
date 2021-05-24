package simsys.core.condition;

import java.util.function.Predicate;
import simsys.core.context.SimulationContext;

/**
 *
 */
public class TimeStopCondition implements Predicate<SimulationContext> {

  /**
   *
   */
  private final double simulationDuration;

  public TimeStopCondition(double simulationDuration) {
    this.simulationDuration = simulationDuration;
  }

  /**
   * Evaluates this predicate on the given argument.
   *
   * @param context the context of the simulation model
   * @return {@code true} if the event provider is empty,
   * otherwise {@code false}
   */
  @Override
  public boolean test(SimulationContext context) {
    return context.getCurrentTime() > this.simulationDuration;
  }

}
