package simsys.core.event;

import simsys.core.event.handler.EventHandler;

public class PeriodicEvent extends HandledEvent {

    public PeriodicEvent(double activateTime) {
        this.activateTime = activateTime;
        this.eventHandler = null;
    }

    public PeriodicEvent(double activateTime, EventHandler<HandledEvent> eventHandler) {
        this.activateTime = activateTime;
        this.eventHandler = eventHandler;
    }
}
