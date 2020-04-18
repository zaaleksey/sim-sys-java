package simsys.core;

import simsys.core.model.SimulationContext;

import java.util.List;

public interface SimulationComponent {
    String getId();

    List<SimulationComponent> getConnectedComponents();

    SimulationContext getSimulationContext();

    void send();

    void receive(Object obj);
}
