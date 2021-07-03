package simsys.core.agent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ReflectionUtils;
import simsys.core.annotation.Action;
import simsys.core.context.SimulationContext;
import simsys.core.event.HandledEvent;
import simsys.core.event.HandledEvent.HandledEventBuilder;
import simsys.core.exception.AgentsCollision;
import symsys.statistic.StatisticAccumulator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Function;

/**
 * Abstract implementation of the Agent interface with the implementation of basic methods.
 */
@Slf4j
public abstract class AbstractAgent implements Agent {

  /**
   * The context of the simulation model.
   */
  protected SimulationContext context;

  /**
   * Agent name. Agent name must be unique within the context.
   */
  protected String agentName;
  protected Map<String, Function<Double, Void>> monitors;

  public AbstractAgent(String agentName) {
    this.agentName = agentName;
  }

  /**
   * Logging values for collecting and calculating characteristics.
   *
   * @param name feature name
   * @param value transmitted value for logging
   */
  public void logValue(String name, double value) {
    context.logVariable(withName(name), value);
  }

  /**
   * Returns an object with statistics by its name.
   *
   * @param name feature name
   * @return statistics for variable
   */
  public StatisticAccumulator getStatisticForVariable(String name) {
    return context.getStatisticForVariable(withName(name));
  }

  protected String withName(String suffix, String separator) {
    return agentName + separator + suffix;
  }

  protected String withName(String suffix) {
    return withName(suffix, ":");
  }


  /**
   * Executes an agent method by method name.
   *
   * @param name method name
   */
  protected void runMethodByName(String name) {
    System.out.println("runMethodByName = " + name);
    Map<String, Method> methodResolver = new HashMap<>();
    Map<String, HandledEvent> eventResolver = new HashMap<>();

    Method[] methods = this.getClass().getDeclaredMethods();
    for (Method method : methods) {
      Annotation[] annotations = method.getDeclaredAnnotations();
      for (Annotation annotation : annotations) {
        if (annotation.annotationType().equals(Action.class)) {
          Action actionAnnotation = (Action) annotation;
          methodResolver.put(actionAnnotation.name(), method);
          eventResolver.put(actionAnnotation.name(), new HandledEvent());
        }
      }
    }

    var methodToRun = methodResolver.get(name);
    LOGGER.debug("Invoke method: {}", methodToRun.getName());
    methodToRun.setAccessible(true);
    ReflectionUtils.invokeMethod(methodToRun, this);

    System.out.println("Current time = " + context.getCurrentTime());
  }

  protected void logInternalVariables() {
    // TODO: implement method. log all internal variables marked with Stat annotation
    //  do it over time (like the average number of customers) maybe need add additional method to context
  }

  /**
   * Performing actions passed as a parameter. Calls performActionAfterTimeout method with zero delay.
   *
   * @param action action to be performed
   */
  @Override
  public void performAction(AgentAction action) {
    performActionAfterTimeout(action, 0);
  }

  /**
   * Performing actions passed as a parameter, after a delay. The action is wrapped in a handled event and
   * is scheduled to run.
   *
   * @param action action to be performed
   * @param delay perform an action delay
   */
  @Override
  public void performActionAfterTimeout(AgentAction action, double delay) {

    HandledEvent event = new HandledEventBuilder(context)
        .addHandler(e -> {
              action.run();
            }
        ).build();
    event.setActivateTime(context.getCurrentTime() + delay);
    context.getEventProvider().add(event);
  }

  /**
   * Sets the context of the simulation model.
   *
   * @param context the context of the simulation model
   */
  @Override
  public void setContext(SimulationContext context) {
    this.context = context;
  }

  /**
   * Returns the name of the Agent.
   *
   * @return the name of the Agent
   */
  @Override
  public String getName() {
    return agentName;
  }

}
