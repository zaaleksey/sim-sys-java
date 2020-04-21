package simsys.core.condition;

import simsys.core.context.SimulationContext;

import java.util.function.Predicate;

public class EmptyStopCondition implements Predicate<SimulationContext> {


    @Override
    public boolean test(SimulationContext simulationContext) {
        return simulationContext.getEventProvider().isEmpty();
    }
}
