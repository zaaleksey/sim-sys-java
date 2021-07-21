package simsys.core.condition;

import java.util.function.Predicate;
import simsys.core.context.SimulationContext;

/**
 * The condition for stopping the simulation model when there are no more events in the provider.
 */
public class EmptyStopCondition implements Predicate<SimulationContext> {

  /**
   * Evaluates this predicate on the given argument. The condition for the presence of elements in
   * the provider.
   *
   * @param context the context of the simulation model
   * @return {@code true} if the event provider is empty, otherwise {@code false}
   */
  @Override
  public boolean test(SimulationContext context) {
    return context.getEventProvider().isEmpty();
  }

}
