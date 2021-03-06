package simsys.core.environment;

import java.util.HashMap;
import lombok.SneakyThrows;
import simsys.core.component.SimulationComponent;
import simsys.core.exception.NoItemInCollection;

public class EnvironmentImpl<T extends SimulationComponent> implements Environment<T> {

  private final HashMap<String, SimulationComponent> simulationComponents;

  public EnvironmentImpl() {
    this.simulationComponents = new HashMap<>();
  }

  @Override
  public HashMap<String, SimulationComponent> getComponents() {
    return this.simulationComponents;
  }

  @Override
  public void addComponent(String id, SimulationComponent component) {
    this.simulationComponents.put(id, component);
  }

  @SneakyThrows
  @Override
  public T getComponent(String id) {
    if (this.simulationComponents.get(id) == null) {
      throw new NoItemInCollection(id);
    }
    return (T) this.simulationComponents.get(id);
  }

}
