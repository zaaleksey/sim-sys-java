package simsys.core;

import java.util.List;
import simsys.core.component.SimulationComponent;
import simsys.core.context.SimulationContext;
import simsys.core.exception.NoItemInCollection;

public abstract class Node {

  protected String id;
  protected List<SimulationComponent> connectedComponents;
  protected SimulationContext simulationContext;

  public void addConnection(SimulationComponent component) {
    connectedComponents.add(component);
  }

  public void addAllConnection(List<SimulationComponent> components) {
    connectedComponents.addAll(components);
  }

  public SimulationComponent getComponentById(String id) throws NoItemInCollection {
    for (SimulationComponent component : connectedComponents) {
      if (id.equals(component.getId())) {
        return component;
      }
    }
    throw new NoItemInCollection(id);
  }

  public String getId() {
    return id;
  }


  public SimulationContext getSimulationContext() {
    return null;
  }

}
