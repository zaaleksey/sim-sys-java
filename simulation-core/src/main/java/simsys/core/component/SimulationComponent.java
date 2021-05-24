package simsys.core.component;

import simsys.core.context.SimulationContext;
import simsys.core.event.Event;

import java.util.Collection;

public interface SimulationComponent {

  String getId();

  SimulationContext getSimulationContext();

  Collection<Event> getEvents();

  Collection<Entity> getEntities();

}
