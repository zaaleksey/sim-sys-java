package simsys.core.component;

import simsys.core.context.SimulationContext;
import simsys.core.event.Event;

import java.util.Collection;

/**
 * An interface that is a component of the simulation model.
 */
public interface SimulationComponent {

  /**
   * Returns the id of the component.
   *
   * @return the id of the component
   */
  String getId();

  /**
   * Returns the context of the simulation model.
   *
   * @return the context of the simulation model
   */
  SimulationContext getSimulationContext();

  /**
   * Returns the events of this simulation model component.
   *
   * @return the events of this simulation model component
   */
  Collection<Event> getEvents();

  /**
   * Returns the entities of this simulation model component.
   *
   * @return the entities of this simulation model component
   */
  Collection<Entity> getEntities();

}
