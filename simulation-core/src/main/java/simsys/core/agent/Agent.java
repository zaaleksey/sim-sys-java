package simsys.core.agent;

import simsys.core.context.SimulationContext;

public interface Agent {

  String getName();

  void performAction(AgentAction action);

  void performActionAfterTimeout(AgentAction action, double delay);

  void setContext(SimulationContext context);

}
