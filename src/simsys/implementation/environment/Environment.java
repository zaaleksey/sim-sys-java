package simsys.implementation.environment;

import simsys.implementation.containers.DemoEventContainer;
import simsys.api.containers.EventContainer;

public class Environment {

    private double currentTime = 0D;
    private EventContainer eventContainer;

    public Environment(EventContainer eventContainer ) {
        this.eventContainer = eventContainer;
    }

    public EventContainer getEventContainer() {
        return eventContainer;
    }
}
