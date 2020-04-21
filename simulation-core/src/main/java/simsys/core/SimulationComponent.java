package simsys.core;

import simsys.core.environment.Entity;
import simsys.core.event.Event;
import simsys.core.context.SimulationContext;

import java.util.Collection;


// send receive слишком избыточное описание
public interface SimulationComponent {
    String getId();

    SimulationContext getSimulationContext();

    Collection<Event> getEvents();

    Collection<Entity> getEntities();
}
