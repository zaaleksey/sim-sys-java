package simsys.core.environment;

import java.util.HashMap;
import lombok.SneakyThrows;
import simsys.core.component.SimulationComponent;
import simsys.core.exception.NoItemInCollection;

/**
 * Basic implementation of the simulation Environment.
 *
 * @param <T> type of components of this environment
 */
public class EnvironmentImpl<T extends SimulationComponent> implements Environment<T> {

  /**
   * Collection for storing simulation model components.
   */
  private final HashMap<String, SimulationComponent> simulationComponents;

  public EnvironmentImpl() {
    this.simulationComponents = new HashMap<>();
  }

  /**
   * Returns the collection Map of components found in this environment.
   *
   * @return the collection Map of components
   */
  @Override
  public HashMap<String, SimulationComponent> getComponents() {
    return simulationComponents;
  }

  /**
   * Adds a component by id to the simulation environment. The component is added to the collection
   * HashMap.
   *
   * @param id        component id
   * @param component added component
   */
  @Override
  public void addComponent(String id, SimulationComponent component) {
    simulationComponents.put(id, component);
  }

  /**
   * Returns a component from the environment by id.
   *
   * @param id id component
   * @return the component
   * @throws NoItemInCollection if no component was found in the collection by id
   */
  @SneakyThrows
  @Override
  public T getComponent(String id) {
    if (simulationComponents.get(id) == null) {
      throw new NoItemInCollection(id);
    }
    return (T) simulationComponents.get(id);
  }

}
