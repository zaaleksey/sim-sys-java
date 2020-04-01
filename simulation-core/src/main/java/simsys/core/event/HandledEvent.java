package simsys.core.event;

import simsys.core.event.handler.EventHandler;

public class HandledEvent implements Event {
    protected double activateTime;
    protected EventHandler<HandledEvent> eventHandler;

    @Override
    public double getActivateTime() {
        return this.activateTime;
    }

    @Override
    public void setActivateTime(double activateTime) {
        this.activateTime = activateTime;
    }

    public void activate() {
        eventHandler.handle(this);
    }

    public void addHandler(EventHandler eventHandler) {
        if (this.eventHandler == null) {
            this.eventHandler = eventHandler;
        } else {
            eventHandler.addNext(eventHandler);
        }
    }

    @Override
    public int compareTo(Event event) {
        return Double.compare(this.activateTime, event.getActivateTime());
    }
}
