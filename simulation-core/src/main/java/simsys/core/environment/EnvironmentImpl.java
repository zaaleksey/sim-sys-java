package simsys.core.environment;

import simsys.core.SimulationComponent;

import java.util.HashMap;

public class EnvironmentImpl implements Environment {

    private HashMap<String, SimulationComponent> simulationComponents = new HashMap<>();

    @Override
    public HashMap<String, SimulationComponent> getComponents() {
        return simulationComponents;
    }

    public EnvironmentImpl() {
        simulationComponents = new HashMap<>();
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
