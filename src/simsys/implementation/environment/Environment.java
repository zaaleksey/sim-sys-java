package simsys.implementation.environment;

import simsys.api.containers.EventManager;

public class Environment {

    private EventManager eventManager;

    private Environment(EventManager eventManager) {
        this.eventManager = eventManager;
    }

    public EventManager getEventManager() {
        return eventManager;
    }

    public static Environment createEnvironmetn(EventManager eventManager) {
        return new Environment(eventManager);
    }
}
