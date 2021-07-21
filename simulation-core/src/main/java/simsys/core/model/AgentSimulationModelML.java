package simsys.core.model;

import simsys.core.agent.Agent;
import simsys.core.context.SimulationContext;
import simsys.core.context.SimulationContextImpl;

public class AgentSimulationModelML extends AgentBasedSimulationModel {

  public AgentSimulationModelML(SimulationContext simulationContext) {
    super(simulationContext);
  }

  // обнуляем контектс у всех
  public void reset() {
    this.simulationContext = SimulationContextImpl.getContext();
    for (Agent agent : agents) {
      agent.setContext(this.simulationContext);
    }
  }

}
