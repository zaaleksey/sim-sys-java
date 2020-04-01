package simsys.core.model;

import simsys.core.event.Event;

import java.util.function.Predicate;

public abstract class AbstractSimulationModel implements SimulationModel {
    protected SimulationContext simulationContext;
    private Predicate<SimulationContext> stopCondition;
    private SimulationContext context;


    @Override
    public void step() {
        Event nextEvent = simulationContext.getEventProvider().getNext();
        nextEvent.activate();
    }

    @Override
    public void run() {
        while (stopCondition.test(context)) {
            step();
        }
    }
}
