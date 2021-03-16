package simsys.core.agent;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.util.ReflectionUtils;
import simsys.core.annotation.Action;
import simsys.core.annotation.State;
import simsys.core.context.SimulationContext;
import simsys.core.event.HandledEvent;
import simsys.core.event.handler.StatisticStateHandler;


// Wrapper-adapter for agent as event
public class AgentEventImpl implements AgentEvent {

  protected Agent agent;
  protected SimulationContext simulationContext;

  Set<String> states;
  List<HandledEvent> events;

  public AgentEventImpl(Agent agent, SimulationContext simulationContext) {
    this.agent = agent;
    this.simulationContext = simulationContext;
    setAgent(agent);
  }

  @Override
  public Agent getAgent() {
    return this.agent;
  }

  @Override
  public void setAgent(Agent agent) {
    System.out.println("Agent name: " + agent.getClass().getName());
    // create statistic handler for all states
    StatisticStateHandler statisticHandler =
        new StatisticStateHandler(agent, this.simulationContext);

    defineAllAgentStates();
    String initialState = getInitialState();

    // create map State->Method
    Map<String, Method> methodResolver = new HashMap<>();
    Map<String, HandledEvent> eventResolver = new HashMap<>();

    Method[] methods = agent.getClass().getDeclaredMethods();

    for (Method method : methods) {
      Annotation[] annotations = method.getDeclaredAnnotations();
      for (Annotation annotation : annotations) {
        if (annotation.annotationType().equals(Action.class)) {
          String[] statesFromAnnotation = ((Action) annotation).states();
          for (String state : statesFromAnnotation) {
            if (!this.states.contains(state)) {
              throw new IllegalStateException("There is no a state with name "
                  + state);
            }
            methodResolver.put(state, method);
            eventResolver.put(state, new HandledEvent()
                .addHandler(statisticHandler));
          }
        }
      }
    }

//    ???
    this.events = new ArrayList<>();

    Field currentStateFiled = ReflectionUtils
        .findField(agent.getClass(),
            "currentState");
    currentStateFiled.setAccessible(true);

    Field nextStateField = ReflectionUtils
        .findField(agent.getClass(), "nextState");
    nextStateField.setAccessible(true);

    Field nextActivationTimeField = ReflectionUtils
        .findField(agent.getClass(), "nextActivationTime");
    nextActivationTimeField.setAccessible(true);

    // we create event per method
    for (Map.Entry<String, Method> response : methodResolver.entrySet()) {
      HandledEvent event = eventResolver.get(response.getKey());
      Method method = response.getValue();
      event.addHandler(e -> {
        // first off all invoke  agent method
        method.setAccessible(true);
        ReflectionUtils.invokeMethod(method, agent);
        // after this agent was pre-updated
        Object nextState = ReflectionUtils.getField(nextStateField, agent);
        ReflectionUtils.setField(currentStateFiled, agent, nextState);

        System.out.println("Next state: " + nextState + " for current state: " + currentStateFiled.get(agent));
        // it means the next state is defined = makes sense
        // we need create the next event
        if (nextState != null) {
          HandledEvent nextEvent = eventResolver.get(nextState);
          Object nextActivationTime = ReflectionUtils
              .getField(nextActivationTimeField, agent);
          nextEvent.setActivateTime((double) nextActivationTime);
          this.simulationContext.getEventProvider().add(nextEvent);
        }
      });

    }

    this.simulationContext.getEventProvider().add(eventResolver.get(initialState));
    // set initial value from annotation
    // also we can consider a case with init method or initialization within constructor
    ReflectionUtils.setField(currentStateFiled, agent, initialState);

    System.out.println("Print states and corresponding actions");
    for (Map.Entry<String, HandledEvent> pair : eventResolver.entrySet()) {
      System.out.println(pair.getKey() + ": " + pair.getValue());
    }
    System.out.println("*******************************************");

  }

  private void defineAllAgentStates() {
    this.states = new HashSet<>();

    Field[] fields = this.agent.getClass().getDeclaredFields();
    for (Field field : fields) {
      Annotation[] annotations = field.getDeclaredAnnotations();
      for (Annotation annotation : annotations) {
        if (annotation.annotationType().equals(State.class)) {
          field.setAccessible(true);
          String stateName = (String) ReflectionUtils.getField(field, this.agent);
          states.add(stateName);
        }
      }
    }
  }

  private String getInitialState() {
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
      throw new IllegalStateException("There is no initial state");
    }

    System.out.println("Found the initial state of the agent: " + initialState);
    return initialState;
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
