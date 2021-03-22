package simsys.core.environment;

import java.util.Map;
import simsys.core.component.SimulationComponent;

public interface Environment<T extends SimulationComponent> {

  Map<String, SimulationComponent> getComponents();

  void addComponent(String id, T entity);

  T getComponent(String id);

}
