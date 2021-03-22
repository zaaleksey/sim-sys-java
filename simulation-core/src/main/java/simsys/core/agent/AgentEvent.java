package simsys.core.agent;

import simsys.core.event.Event;

public interface AgentEvent extends Event {

  Agent getAgent();
  
  void setAgent(Agent agent);

}
