package simsys.implementation.environment;

import simsys.implementation.containers.DemoEventContainer;
import simsys.api.containers.EventContainer;

public class Environment {

    private double currentTime;
    private EventContainer eventContainer;

    private Environment(double currentTime, EventContainer eventContainer) {
        this.currentTime = currentTime;
        this.eventContainer = eventContainer;
    }

    public EventContainer getEventContainer() {
        return eventContainer;
    }

    public double getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(double currentTime) {
        this.currentTime = currentTime;
    }

    public static Environment createEnvironmetn(double currentTime, EventContainer eventContainer) {
        return new Environment(currentTime, eventContainer);
    }

    public static Environment createEnvironmetn(EventContainer eventContainer) {
        return new Environment(0, eventContainer);
    }
}
