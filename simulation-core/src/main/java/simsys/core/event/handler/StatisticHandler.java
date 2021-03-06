package simsys.core.event.handler;

import java.util.HashMap;
import java.util.Set;
import simsys.core.agent.AgentEvent;
import simsys.core.context.SimulationContext;
import simsys.core.event.Event;

// only agents can use this class statistics
public class StatisticHandler implements EventHandler {

  protected SimulationContext simulationContext;

  private final HashMap<String, Double> timeInStates;

  public StatisticHandler() {
    this.timeInStates = new HashMap<>();
  }

  public StatisticHandler(Set<String> states) {
    this.timeInStates = new HashMap<>();
    for (String state : states) {
      timeInStates.put(state, 0D);
    }
  }

  @Override
  public void handle(Event event) {
    // for state in states: timeInStates[states] += delta (in Environment)
    AgentEvent agentEvent = (AgentEvent) event;

    String state = agentEvent.getAgent().currentState();
    double updateTime = this.timeInStates.get(state) + 0;
//        this.simulationContext.getDeltaTimeLastTwoEvents();
    this.timeInStates.put(state, updateTime);

    System.out.println(this.timeInStates);
  }

}
