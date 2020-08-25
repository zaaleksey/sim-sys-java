package simsys.core.agent;

import simsys.core.event.Event;

public interface AgentEvent extends Event {

  public Agent getAgent();

  public Agent setAgent(Agent agent);


}
