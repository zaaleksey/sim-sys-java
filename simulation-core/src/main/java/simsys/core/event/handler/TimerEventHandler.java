package simsys.core.event.handler;

import simsys.core.carrier.ExponentialCarrier;
import simsys.core.model.SimulationContext;

import java.util.Random;

public class TimerEventHandler extends AbstractEventHandler {
    public TimerEventHandler(Random random, double rate, SimulationContext simulationContext) {
        this.simulationContext = simulationContext;
        this.randomVariable = new ExponentialCarrier(random, rate);
    }
}
