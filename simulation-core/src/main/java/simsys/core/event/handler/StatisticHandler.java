package simsys.core.event.handler;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.springframework.util.ReflectionUtils;
import simsys.core.agent.Agent;
import simsys.core.agent.AgentEvent;
import simsys.core.annotation.State;
import simsys.core.annotation.Statistic;
import simsys.core.context.SimulationContext;
import simsys.core.event.Event;

public class StatisticHandler implements EventHandler {

  protected SimulationContext simulationContext;

  private final Agent agent;
  private final HashMap<String, Double> timeInStates;

  public StatisticHandler(Agent agent, SimulationContext simulationContext) {
    this.agent = agent;
    this.simulationContext = simulationContext;
    this.timeInStates = initStatesMap();
  }

  private HashMap<String, Double> initStatesMap() {
    Set<String> states = new HashSet<>();
    HashMap<String, Double> timeInStates = new HashMap<>();

    Field[] fields = this.agent.getClass().getDeclaredFields();
    for (Field field : fields) {
      Annotation[] annotations = field.getDeclaredAnnotations();
      for (Annotation annotation : annotations) {
        if (annotation.annotationType().equals(Statistic.class)) {
          field.setAccessible(true);
          String stateName = (String) ReflectionUtils.getField(field, agent);
          states.add(stateName);
        }
      }
    }

    for (String state: states) {
      timeInStates.put(state, 0D);
    }

    return timeInStates;
  }

  public void setSimulationContext(SimulationContext simulationContext) {
    this.simulationContext = simulationContext;
  }


  @Override
  public void handle(Event event) {
    // for state in states: timeInStates[states] += delta (in Environment)

    String state = this.agent.currentState();
    double updateTime = this.timeInStates.get(state) +
        this.simulationContext.getDeltaTimeLastTwoEvents();
    this.timeInStates.put(state, updateTime);

    System.out.println("Time in states: " + this.timeInStates);
  }

}
