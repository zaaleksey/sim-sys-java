package simsys.core.agent;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ReflectionUtils;
import simsys.core.annotation.Action;
import simsys.core.context.SimulationContext;
import simsys.core.event.HandledEvent;
import simsys.core.event.HandledEvent.HandledEventBuilder;
import symsys.statistic.StatisticAccumulator;

@Slf4j
public abstract class AbstractAgent implements Agent {

  protected SimulationContext simulationContext;

  protected String agentName;
  protected Map<String, Function<Double, Void>> monitors;

  //TODO: либо через аннотацию
  // либо через список методов для сбора значений

  public AbstractAgent(String agentName) {
    this.agentName = agentName;
  }

  //TODO: имя агента должно быть уникально внутри контекста!
  // возможно сделать проверку при добавлении на это 
  // так как оно формирует имя при логировании
  public void logValue(String name, double value) {
    simulationContext.logVariable(agentName + ':' + name, value);
  }

  // TODO: дублирование кода - конкатенация через двоеточие
  public StatisticAccumulator getStatisticForVariable(String name) {
    return simulationContext.getStatisticForVariable(agentName + ':' + name);
  }


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

    System.out.println("Current time = " + simulationContext.getCurrentTime());
  }

  @Override
  public void performAction(AgentAction action) {
    performActionAfterTimeout(action, 0);
  }


  // TODO: log all internal variables marked with Stat annotation
  // do it over time (like the average number of customers)
  // maybe need add additional method to context
  protected void logInternalVariables() {

  }

  @Override
  public void performActionAfterTimeout(AgentAction actionName, double delay) {

    HandledEvent event = new HandledEventBuilder(simulationContext)
        .addHandler(e -> {
              actionName.run();
            }
        ).build();
    event.setActivateTime(simulationContext.getCurrentTime() + delay);
    simulationContext.getEventProvider().add(event);
  }

  @Override
  public void setContext(SimulationContext simulationContext) {
    this.simulationContext = simulationContext;
  }

  @Override
  public String getName() {
    return agentName;
  }

}
