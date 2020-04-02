package simsys.core.model;

import simsys.core.event.Event;

import java.util.function.Predicate;

public abstract class AbstractSimulationModel implements SimulationModel {
    protected SimulationContext simulationContext;
    //перенести классические stopConditions в отдельные класссы
    private Predicate<SimulationContext> stopCondition = ctx -> ctx.getEventProvider().isEmpty();



    @Override
    public void step() {
        Event nextEvent = simulationContext.getEventProvider().getNext();
        simulationContext.getClock().setCurrentTime(nextEvent.getActivateTime());
        System.out.println("Current clock time: " + simulationContext.getCurrentTime());
        nextEvent.activate();
        System.out.println("=========");
    }

    @Override
    public void run() {
        while (!stopCondition.test(simulationContext)) {
            step();
        }
    }

    //возможно, что нужно куда-то перенести
    public void runWithStopCondition(Predicate<SimulationContext> stopCondition) {
        while (!stopCondition.test(simulationContext)) {
            step();
        }
    }
}
