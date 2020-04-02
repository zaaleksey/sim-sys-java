package simsys.core.event.handler;

import simsys.core.event.Event;
import simsys.core.model.SimulationContext;
import simsys.core.random.ExponentialRandom;
import simsys.core.random.RandomVariable;

import java.util.Random;

public class TimerEventHandler implements EventHandler {

    RandomVariable variable = new ExponentialRandom(new Random(), 0.5);
    SimulationContext simulationContext;

    public TimerEventHandler(SimulationContext simulationContext) {
        this.simulationContext = simulationContext;
    }

    @Override
    public void handle(Event event) {
        System.out.println("Timer time: " + event.getActivateTime());
        event.postpone(variable.nextValue());
        //такое событие нужно запланировать
        simulationContext.getEventProvider().add(event);
    }

    @Override
    public EventHandler<?> getNext() {
        return null;
    }

    @Override
    public void addNext(EventHandler next) {

    }
}
