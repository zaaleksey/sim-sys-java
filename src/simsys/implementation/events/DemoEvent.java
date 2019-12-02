package simsys.implementation.events;

import simsys.implementation.environment.Environment;
import simsys.api.events.Event;

import java.util.Random;

public class DemoEvent extends Event {

    private Environment environment;
    private Random random = new Random(3);

    public DemoEvent(Environment environment, double actionEventTime) {
        this.environment = environment;
        this.actionEventTime = actionEventTime;
    }

    @Override
    public void actionEvent() {
        environment.getEventContainer().addEvent(new DemoEvent(environment,actionEventTime + random.nextDouble()));
        System.out.println("Time " + getActionEventTime());
    }
}
