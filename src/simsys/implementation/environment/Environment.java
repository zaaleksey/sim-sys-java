package simsys.implementation.environment;

import simsys.api.containers.EventContainer;
import simsys.mm1.InfoMM1;

public class Environment {

    private InfoMM1 info = InfoMM1.INFO;
    private EventContainer eventContainer;

    private Environment(EventContainer eventContainer) {
        this.eventContainer = eventContainer;
    }

    public EventContainer getEventContainer() {
        return eventContainer;
    }

    public static Environment createEnvironmetn(EventContainer eventContainer) {
        return new Environment(eventContainer);
    }
}
