package simsys.core.environment;

import simsys.core.component.SimulationComponent;

import java.util.Map;

/**
 * Simulation environment interface. Contains all components of the model.
 *
 * @param <T> component type
 */
public interface Environment<T extends SimulationComponent> {

  /**
   * Returns the collection Map of components found in this environment.
   *
   * @return the collection Map of components
   */
  Map<String, SimulationComponent> getComponents();

  /**
   * Adds a component by id to the simulation environment.
   *
   * @param id component id
   * @param component added component
   */
  void addComponent(String id, T component);

  /**
   * Returns a component from the environment by id.
   *
   * @param id id component
   * @return the component
   */
  T getComponent(String id);

}
