package simsys.core.model;

import simsys.core.agent.Agent;
import simsys.core.context.SimulationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Implementation of the simulation model by agents. Inherits the abstract class {@code AbstractSimulationModel}.
 */
public class AgentBasedSimulationModel extends AbstractSimulationModel {

  /**
   * A list containing the agents of the simulation model.
   */
  protected List<Agent> agents;

  public AgentBasedSimulationModel(SimulationContext simulationContext) {
    this.simulationContext = simulationContext;
    this.agents = new ArrayList<>();
  }

  /**
   * Adding an agent to the list.
   *
   * @param agent agent to add to the list
   */
  public void addAgent(Agent agent) {
    // TODO: check for the name of the agent when added to the context, since it forms the name when logging
    this.agents.add(agent);
  }

  /**
   * Adding a collection of agents.
   *
   * @param agents collection of agents to add
   */
  public void addAgents(List<Agent> agents) {
    // TODO: check for the name of the agent when added to the context, since it forms the name when logging
    this.agents.addAll(agents);
  }

  /**
   * Returns the agent by its class. Finds all agents with a certain class and adds them to the list.
   * Returns this list of agents if agents were found, otherwise throws an exception {@code NoSuchElementException}.
   *
   * @param clazz agent class
   * @return a list containing agents that belong to the given class
   * @throws NoSuchElementException if no agent found with this class
   */
  public List<Agent> getAgentsByClass(Class<?> clazz) {
    List<Agent> agentsList = new ArrayList<>();
    for (Agent agent : agents) {
      if (agent.getClass() == clazz) {
        agentsList.add(agent);
      }
    }
    if (agentsList.isEmpty()) {
      throw new NoSuchElementException("No agents found with this class " + clazz.getName());
    }
    return agentsList;
  }

  /**
   * Returns a list with all agents.
   *
   * @return a list with agents
   */
  public List<Agent> getAgents() {
    return agents;
  }

}
