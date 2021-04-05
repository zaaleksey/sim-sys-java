package simsys.core.event.handler;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ReflectionUtils;
import simsys.core.agent.Agent;
import simsys.core.annotation.State;
import simsys.core.context.SimulationContext;
import simsys.core.event.Event;

@Slf4j
public class StatisticStateHandler implements EventHandler {

  protected SimulationContext simulationContext;

  private double allTime;
  private String state;
  private final Agent agent;
  private final HashMap<String, Double> timeInStates = new HashMap<>();
  private final HashMap<String, Double> probabilityInStates = new HashMap<>();

  public StatisticStateHandler(Agent agent, SimulationContext simulationContext) {
    this.allTime = 0;
    this.agent = agent;
    this.simulationContext = simulationContext;
    initStatesMap();
  }

  public void setSimulationContext(SimulationContext simulationContext) {
    this.simulationContext = simulationContext;
  }

  @Override
  public void handle(Event event) {
    allTime += simulationContext.getDeltaTimeLastTwoEvents();
    if (timeInStates.get(state) != null) {
      double updateTime = timeInStates.get(state)
          + simulationContext.getDeltaTimeLastTwoEvents();
      timeInStates.put(state, updateTime);
      probabilityInStates.put(state, updateTime / allTime);

      state = agent.currentState();

      LOGGER.debug("All time: " + allTime);
      LOGGER.debug("Time in states: " + timeInStates);
      LOGGER.debug("Probability in states: " + probabilityInStates);
    }
  }

  private void initStatesMap() {
    HashSet<String> states = getAllStatesFromAgent();

    for (String state : states) {
      this.timeInStates.put(state, 0D);
    }
  }

  private HashSet<String> getAllStatesFromAgent() {
    HashSet<String> states = new HashSet<>();

    Field[] fields = this.agent.getClass().getDeclaredFields();
    for (Field field : fields) {
      Annotation[] annotations = field.getDeclaredAnnotations();
      for (Annotation annotation : annotations) {
        if (annotation.annotationType().equals(State.class)) {
          if (((State) annotation).statistic()) {
            field.setAccessible(true);
            String stateName = (String) ReflectionUtils.getField(field, agent);
            states.add(stateName);
            if (((State) annotation).initial()) {
              this.state = stateName;
            }
          }
        }
      }
    }

    return states;
  }

}
