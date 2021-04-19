package simsys.core.agent;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import jdk.internal.net.http.common.Demand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ReflectionUtils;
import simsys.core.annotation.Action;
import simsys.core.annotation.State;
import simsys.core.annotation.Trigger;
import simsys.core.context.SimulationContext;
import simsys.core.event.HandledEvent;
import simsys.core.event.handler.StatisticStateHandler;

@Slf4j
public class AgentEventImpl implements AgentEvent {

  protected SimulationContext simulationContext;
  protected Agent agent;

  private Map<String, HandledEvent> eventResolver;

  public AgentEventImpl(SimulationContext simulationContext, Agent agent) {
    this.simulationContext = simulationContext;
    setAgent(agent);
  }

  @Override
  public Agent getAgent() {
    return this.agent;
  }

  @Override
  public void setAgent(Agent agent) {
    this.agent = agent;
    defineAllStatesInAgent();
    agentHandling();
  }

  private void agentHandling() {
    LOGGER.debug("Agent name: " + this.agent.getClass().getName());

    // create map State->Method
    this.eventResolver = new HashMap<>();
    Map<String, Method> methodResolver = new HashMap<>();

    // create statistic handler for all states
    StatisticStateHandler statisticHandler =
        new StatisticStateHandler(this.agent, this.simulationContext);

    Method[] methods = this.agent.getClass().getDeclaredMethods();
    for (Method method : methods) {
      Annotation[] annotations = method.getDeclaredAnnotations();
      for (Annotation annotation : annotations) {
        if (annotation.annotationType().equals(Action.class)) {
          String[] statesFromAnnotation = ((Action) annotation).states();
          for (String state : statesFromAnnotation) {
            if (!this.agent.getStates().contains(state)) {
              throw new IllegalStateException("There is no a state with name "
                  + state);
            }
            methodResolver.put(state, method);
            eventResolver.put(state, new HandledEvent());
          }
        }
      }
    }

    Field currentStateFiled = getFieldByName("currentState");
    Field nextStateField = getFieldByName("nextState");
    Field nextActivationTimeField = getFieldByName("nextActivationTime");

    // we create event per method
    for (Map.Entry<String, Method> response : methodResolver.entrySet()) {
      HandledEvent event = eventResolver.get(response.getKey());
      Method method = response.getValue();
      event
          .addHandler(statisticHandler)
          .addHandler(e -> {
        method.setAccessible(true);
        LOGGER.debug("Invoke method: " + method.getName());
        if (method.isAnnotationPresent(Trigger.class)) {
          System.out.println("YEEES, TRIGGER!!! " + method.getName());

          Trigger triggerAnnotation = method.getAnnotation(Trigger.class);
          Class<?>[] clazz = triggerAnnotation.clazz();
          String methodName = triggerAnnotation.methodName();

          // обернуть все что будет передаваться через триггер в класс Package (посылка) ???
          ReflectionUtils.invokeMethod(method, this.agent);
          System.out.println();

        } else {
          ReflectionUtils.invokeMethod(method, this.agent);
        }

        Object nextState = ReflectionUtils.getField(nextStateField, this.agent);
        ReflectionUtils.setField(currentStateFiled, this.agent, nextState);

        // it means the next state is defined = makes sense
        // we need create the next event
        if (nextState != null) {
          HandledEvent nextEvent = eventResolver.get(nextState);
          Object nextActivationTime = ReflectionUtils
              .getField(nextActivationTimeField, this.agent);
          nextEvent.setActivateTime((double) nextActivationTime);
          this.simulationContext.getEventProvider().add(nextEvent);
        }
      });
    }

    String initialState = getInitialStateFromAgent();
    this.simulationContext.getEventProvider().add(eventResolver.get(initialState));
    ReflectionUtils.setField(currentStateFiled, this.agent, initialState);

    printStateAndCorrespondingActions();
  }

  private void defineAllStatesInAgent() {
    this.agent.setStates(new HashSet<>());

    Field[] fields = this.agent.getClass().getDeclaredFields();
    for (Field field : fields) {
      if (field.isAnnotationPresent(State.class)) {
        field.setAccessible(true);
        String stateName = (String) ReflectionUtils.getField(field, this.agent);
        this.agent.getStates().add(stateName);
      }
    }
  }

  private String getInitialStateFromAgent() {
    String initialState = null;
    Field[] fields = this.agent.getClass().getDeclaredFields();
    for (Field field : fields) {
      Annotation[] annotations = field.getDeclaredAnnotations();
      for (Annotation annotation : annotations) {
        if (annotation.annotationType().equals(State.class)) {
          if (((State) annotation).initial()) {
            field.setAccessible(true);
            initialState = (String) ReflectionUtils.getField(field, this.agent);
          }
        }
      }
    }

    if (initialState == null) {
      throw new IllegalStateException("There is no initial state!");
    }

    LOGGER.debug("Found the initial state of the agent: " + initialState + "\n");
    return initialState;
  }

  private Field getFieldByName(String nameField) {
    Field field = ReflectionUtils.findField(this.agent.getClass(), nameField);
    assert field != null;
    field.setAccessible(true);
    return field;
  }

  private void printStateAndCorrespondingActions() {
    LOGGER.debug("Print states and corresponding actions");
    for (Map.Entry<String, HandledEvent> pair : eventResolver.entrySet()) {
      LOGGER.debug(pair.getKey() + ": " + pair.getValue());
    }
    LOGGER.debug("*******************************************\n");
  }

  @Override
  public double getActivateTime() {
    return 0;
  }

  @Override
  public void setActivateTime(double activateTime) {

  }

  @Override
  public void activate() {

  }
}
