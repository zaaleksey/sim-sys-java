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
    agents = new ArrayList<>();
  }

  public void addAgent(Agent agent) {
    agents.add(agent);
    AgentEvent event = new AgentEventImpl(simulationContext, agent);
    this.simulationContext.getEventProvider().add(event);
  }

}
