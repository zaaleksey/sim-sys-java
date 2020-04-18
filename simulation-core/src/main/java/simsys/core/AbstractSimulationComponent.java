package simsys.core;

import simsys.core.exception.NoItemInCollection;
import simsys.core.model.SimulationContext;

import java.util.List;

public abstract class AbstractSimulationComponent implements SimulationComponent {

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
        for (SimulationComponent component: connectedComponents) {
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
    public List<SimulationComponent> getConnectedComponents() {
        return null;
    }

    @Override
    public SimulationContext getSimulationContext() {
        return null;
    }
}
