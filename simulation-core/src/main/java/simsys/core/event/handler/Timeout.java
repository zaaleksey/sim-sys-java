package simsys.core.event.handler;

import simsys.core.event.Event;
import simsys.core.model.SimulationContext;
import simsys.random.RandomVariable;

import java.util.function.Supplier;

public class Timeout implements EventHandler {
    protected SimulationContext simulationContext;


    private Supplier<Double> activateTimes;

    public Timeout(double interval) {
        this.activateTimes = new Supplier<Double>() {
            double nextTime = 0;

            @Override
            public Double get() {
                nextTime += interval;
                return nextTime;
            }
        };
    }

    public Timeout(RandomVariable randomVariable) {
        this.activateTimes = new Supplier<Double>() {
            double nextTime = 0;

            @Override
            public Double get() {
                nextTime += randomVariable.nextValue();
                return nextTime;
            }
        };
    }

    public Timeout(Supplier<Double> activateTimes) {
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
