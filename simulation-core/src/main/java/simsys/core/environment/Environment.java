package simsys.core.environment;

import simsys.core.SimulationComponent;

import java.util.Map;

public interface Environment<T extends SimulationComponent> {
    Map<String, SimulationComponent> getComponents();

    void addComponent(String id, T entity);

    T getComponent(String id);
}
