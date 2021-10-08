package simsys.core.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import simsys.core.agent.Agent;
import simsys.core.context.SimulationContext;
import simsys.core.exception.AgentsCollisionException;

/**
 * Implementation of the simulation model by agents. Inherits the abstract class {@code
 * AbstractSimulationModel}.
 */
public class AgentBasedSimulationModel extends AbstractSimulationModel {

  /**
   * A list containing the agents of the simulation model.
   */
  protected List<Agent> agents;

  /**
   * @param simulationContext simulation context
   */
  public AgentBasedSimulationModel(double simulationDuration, SimulationContext simulationContext) {
    super(simulationDuration);
    this.simulationContext = simulationContext;
    this.agents = new ArrayList<>();
  }

  /**
   * Adding a collection of agents. To add a collection, an iterative method call is used {@code
   * addAgent}.
   *
   * @param agents collection of agents to add
   */
  public void addAgents(Collection<Agent> agents) {
    Objects.requireNonNull(agents);
    for (Agent agent : agents) {
      addAgent(agent);
    }
  }

  /**
   * Adding an agent to the list.
   *
   * @param agent agent to add to the list
   */
  public void addAgent(Agent agent) {
    Objects.requireNonNull(agent);
    if (possibleAgentName(agent.getName())) {
      agents.add(agent);
    } else {
      throw new AgentsCollisionException(agent.getName());
    }
  }

  /**
   * Checks for the existence of an agent with the same name. If the agent is unique, everything is
   * fine.
   *
   * @param agentName agent name
   * @return true or false
   */
  private boolean possibleAgentName(String agentName) {
    Objects.requireNonNull(agentName);
    for (Agent agent : agents) {
      if (agentName.equals(agent.getName())) {
        return false;
      }
    }
    return true;
  }

  /**
   * Returns the agent by its class. Finds all agents with a certain class and adds them to the
   * list. Returns this list of agents if agents were found, otherwise throws an exception {@code
   * NoSuchElementException}.
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
   * Returns a list of all agents in the given model.
   *
   * @return a list with agents
   */
  public List<Agent> getAgents() {
    return agents;
  }

}
