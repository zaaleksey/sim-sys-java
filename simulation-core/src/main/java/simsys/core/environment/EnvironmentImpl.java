package simsys.core.environment;

import java.util.HashMap;
import simsys.core.SimulationComponent;

public class EnvironmentImpl implements Environment {

  private final HashMap<String, SimulationComponent> simulationComponents;

  public EnvironmentImpl() {
    simulationComponents = new HashMap<>();
  }

  @Override
  public HashMap<String, SimulationComponent> getComponents() {
    return simulationComponents;
  }

  @Override
  public void addComponent(String id, SimulationComponent component) {
    simulationComponents.put(id, component);
  }

  @Override
  public SimulationComponent getComponent(String id) {
    return simulationComponents.get(id);
  }

}
