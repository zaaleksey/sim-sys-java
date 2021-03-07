package simsys.core.component;

import java.util.Collection;
import simsys.core.context.SimulationContext;
import simsys.core.event.Event;


public interface SimulationComponent {

  String getId();
  SimulationContext getSimulationContext();
  Collection<Event> getEvents();
  Collection<Entity> getEntities();

}
