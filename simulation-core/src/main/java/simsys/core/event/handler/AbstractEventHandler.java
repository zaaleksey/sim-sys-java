package simsys.core.event.handler;

import simsys.core.carrier.Carrier;
import simsys.core.event.Event;
import simsys.core.model.SimulationContext;

public abstract class AbstractEventHandler implements EventHandler {
    Carrier<?> randomVariable;
    SimulationContext simulationContext;

    @Override
    public void handle(Event event) {
        System.out.println("Event '"  + getClass() + "' activate ### Time = " + event.getActivateTime());
        event.postpone((Double)randomVariable.getCarry());
        simulationContext.getEventProvider().add(event);
    }

    @Override
    public EventHandler<?> getNext() {
        return (EventHandler<?>) simulationContext.getEventProvider().getNext();
    }

    @Override
    public void addNext(EventHandler next) {
        simulationContext.getEventProvider().add((Event) next);
    }
}
