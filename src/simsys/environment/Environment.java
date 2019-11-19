package simsys.environment;

import simsys.demo.DemoEventContainer;
import simsys.containers.EventContainer;

public class Environment {

    private double currentTime = 0D;
    private EventContainer eventContainer;

    public Environment() {
        eventContainer = new DemoEventContainer();
    }

    public EventContainer getEventContainer() {
        return eventContainer;
    }
}
