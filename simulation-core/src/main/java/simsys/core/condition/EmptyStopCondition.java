package simsys.core.condition;

import java.util.function.Predicate;
import simsys.core.context.SimulationContext;

public class EmptyStopCondition implements Predicate<SimulationContext> {


  @Override
  public boolean test(SimulationContext simulationContext) {
    return simulationContext.getEventProvider().isEmpty();
  }
}
