package simsys.core.event;

import simsys.core.event.handler.EventHandler;

public class HandledEvent implements Event {
    protected double activateTime;
    protected EventHandler<HandledEvent> eventHandler;

    protected HandledEvent() {}

    public void addHandler(EventHandler<HandledEvent> eventHandler) {
        if (this.eventHandler == null) {
            this.eventHandler = eventHandler;
        } else {
            eventHandler.addNext(eventHandler);
        }
    }

    @Override
    public double getActivateTime() {
        return this.activateTime;
    }

    @Override
    public void setActivateTime(double activateTime) {
        this.activateTime = activateTime;
    }

    @Override
    public final void activate() {
        eventHandler.handle(this);
    }
}
