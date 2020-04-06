package simsys.core.model;

import java.util.function.Predicate;

public class TimerSimulationModel extends AbstractSimulationModel {

    public TimerSimulationModel(SimulationContext simulationContext, Predicate<SimulationContext> stopCondition) {
        this.simulationContext = simulationContext;
        this.stopCondition = stopCondition;
    }

    @Override
    public void run() {
        while (!stopCondition.test(simulationContext)) {
            step();
        }
    }
}
