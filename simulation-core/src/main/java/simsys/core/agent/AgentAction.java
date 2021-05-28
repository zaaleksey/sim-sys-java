package simsys.core.agent;


/**
 * Functional interface. Encapsulates the logic of an agent's action.
 */
@FunctionalInterface
public interface AgentAction {

  /**
   * The logic for performing an action by an agent.
   */
  void run();

}
