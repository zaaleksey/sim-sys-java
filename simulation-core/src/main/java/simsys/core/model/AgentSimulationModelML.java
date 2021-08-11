package simsys.core.model;

import simsys.core.agent.Agent;
import simsys.core.context.SimulationContext;
import simsys.core.context.SimulationContextImpl;

/**
 *
 */
public class AgentSimulationModelML extends AgentBasedSimulationModel {

  /**
   * @param simulationContext simulation context
   */
  public AgentSimulationModelML(double simulationDuration, SimulationContext simulationContext) {
    super(simulationDuration, simulationContext);
  }

  /**
   *
   */
  public void reset() {
    this.simulationContext = SimulationContextImpl.getContext();
    for (Agent agent : agents) {
      agent.setContext(this.simulationContext);
    }
  }

}
