package simsys.core.environment;

import java.util.HashMap;
import simsys.core.SimulationComponent;

public class EnvironmentImpl<T extends SimulationComponent> implements Environment<T> {

  private String currentState;
  private double timeDeltaBetweenStates;
  private final HashMap<String, SimulationComponent> simulationComponents;

  public EnvironmentImpl() {
    this.simulationComponents = new HashMap<>();
  }

  public void setTimeDeltaBetweenStates(double timeDeltaBetweenStates) {
    this.timeDeltaBetweenStates = timeDeltaBetweenStates;
  }

  @Override
  public HashMap<String, SimulationComponent> getComponents() {
    return this.simulationComponents;
  }

  @Override
  public void addComponent(String id, SimulationComponent component) {
    this.simulationComponents.put(id, component);
  }

  @Override
  public T getComponent(String id) {
    return (T) this.simulationComponents.get(id);
  }

}
