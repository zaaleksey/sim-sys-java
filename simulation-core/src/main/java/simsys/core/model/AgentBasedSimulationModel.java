package simsys.core.model;

import java.util.ArrayList;
import java.util.List;
import simsys.core.agent.Agent;
import simsys.core.agent.AgentEvent;
import simsys.core.agent.AgentEventImpl;
import simsys.core.context.SimulationContext;

public class AgentBasedSimulationModel extends AbstractSimulationModel {

  protected List<Agent> agents;

  public AgentBasedSimulationModel(SimulationContext simulationContext) {
    this.simulationContext = simulationContext;
    this.agents = new ArrayList<>();
  }

  public void addAgent(Agent agent) {
    this.agents.add(agent);
//    AgentEvent event = new AgentEventImpl(this.simulationContext, agent);
    new AgentEventImpl(this.simulationContext, agent);
  }

  public void addAgents(List<Agent> agents) {
    this.agents.addAll(agents);
    for (Agent agent : agents) {
      new AgentEventImpl(this.simulationContext, agent);
    }
  }

}
