package simsys.core;

import simsys.core.exception.NoItemInCollection;
import simsys.core.context.SimulationContext;

import java.util.List;

public abstract class Node implements SimulationComponent {

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

    @Override
    public String getId() {
        return id;
    }


    @Override
    public SimulationContext getSimulationContext() {
        return null;
    }
}
