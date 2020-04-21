package simsys.core.condition;

import simsys.core.context.SimulationContext;

import java.util.function.Predicate;

public class TimeStopCondition implements Predicate<SimulationContext> {

    private final double simulationDuration;

    public TimeStopCondition(double simulationDuration) {
        this.simulationDuration = simulationDuration;
    }

    @Override
    public boolean test(SimulationContext simulationContext) {
        return simulationContext.getCurrentTime() > simulationDuration;
    }
}
