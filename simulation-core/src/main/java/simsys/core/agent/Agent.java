package simsys.core.agent;

import simsys.core.context.SimulationContext;

/**
 * Agent interface. Encapsulates information and behavior logic of the simulation model object.
 */
public interface Agent {

  /**
   * Performing actions passed as a parameter.
   *
   * @param action action to be performed
   */
  void performAction(AgentAction action);

  /**
   * Performing actions passed as a parameter, after a delay.
   *
   * @param action action to be performed
   * @param delay perform an action delay
   */
  void performActionAfterTimeout(AgentAction action, double delay);

  /**
   * Sets the context of the simulation model.
   *
   * @param context the context of the simulation model
   */
  void setContext(SimulationContext context);

  /**
   * Returns the name of the Agent.
   *
   * @return the name of the Agent
   */
  String getName();

}
