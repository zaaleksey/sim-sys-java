package simsys.core.event.handler;

import simsys.core.event.Event;
import simsys.core.random.ExponentialRandom;
import simsys.core.random.RandomVariable;

import java.util.Random;

public class TimerEventHandler implements EventHandler {

    RandomVariable variable = new ExponentialRandom(new Random(), 0.5);

    @Override
    public void handle(Event event) {
        System.out.println("Timer time: " + event.getActivateTime());
        event.postpone(variable.nextValue());
    }

    @Override
    public EventHandler<?> getNext() {
        return null;
    }

    @Override
    public void addNext(EventHandler next) {

    }
}
