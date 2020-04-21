package simsys.core.event.handler;

import simsys.core.context.SimulationContext;
import simsys.core.event.Event;
import simsys.random.RandomVariable;

import java.util.function.Supplier;

public class TimeoutHandler implements EventHandler {
    protected SimulationContext simulationContext;


    private Supplier<Double> activateTimes;

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

    public TimeoutHandler(Supplier<Double> activateTimes) {
        this.activateTimes = activateTimes;
    }


    public void setSimulationContext(SimulationContext simulationContext) {
        this.simulationContext = simulationContext;
    }


    @Override
    public void handle(Event event) {
        event.setActivateTime(activateTimes.get());
        simulationContext.getEventProvider().add(event);
    }
}
