package simsys.core.context;


import java.util.HashMap;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import simsys.core.CoreConfig;
import simsys.core.clock.Clock;
import simsys.core.environment.Environment;
import simsys.core.provider.EventProvider;
import simsys.statistic.StatisticAccumulator;

/**
 * Basic interface implementation SimulationContext. All basic methods implemented
 */
public class SimulationContextImpl implements SimulationContext {

  /**
   * Simulation environment.
   */
  @Autowired
  protected Environment environment;
  /**
   * Model time clock in the simulation model.
   */
  @Autowired
  protected Clock clock;
  /**
   * Provider for storing events in the simulation model.
   */
  @Autowired
  protected EventProvider eventProvider;
  /**
   * Collection for logging variable values.
   */
  protected Map<String, StatisticAccumulator> logMap;

  /**
   * Collection for storing variable observers.
   */
  protected Map<String, VariableObserver> observers;
  /**
   *
   */
  protected Map<String, Double> observedVariablesMean;

  /**
   * The value of the time between the last two occurred events.
   */
  private double deltaTimeLastTwoEvents;

  /**
   *
   */
  public SimulationContextImpl() {
    deltaTimeLastTwoEvents = 0;
    logMap = new HashMap<>();
  }

  /**
   * Returns the context of the simulation model. The simulation context is stored in the container
   * of the Spring Application.
   *
   * @return the context of the simulation model
   */
  public static @NotNull SimulationContext getContext() {
    return new AnnotationConfigApplicationContext(CoreConfig.class)
        .getBean(SimulationContext.class);
  }

  /**
   * Returns the time between the last two events.
   *
   * @return the time between the last two events
   */
  @Override
  public double getDeltaTimeLastTwoEvents() {
    return deltaTimeLastTwoEvents;
  }

  /**
   * Sets the time between the last two events.
   *
   * @param delta the time between the last two events
   */
  @Override
  public void setDeltaTimeLastTwoEvents(double delta) {
    this.deltaTimeLastTwoEvents = delta;
  }

  /**
   * Returns a simulation model environment.
   *
   * @return a simulation model environment
   */
  @Override
  public Environment getEnvironment() {
    return environment;
  }

  /**
   * Returns the simulation clock of the model.
   *
   * @return the simulation clock
   */
  @Override
  public Clock getClock() {
    return clock;
  }

  /**
   * Returns the event provider of the simulation model.
   *
   * @return the event provider
   */
  @Override
  public EventProvider getEventProvider() {
    return eventProvider;
  }

  /**
   * Logging values for collecting and calculating characteristics.
   *
   * @param name  feature name
   * @param value transmitted value for logging
   */
  @Override
  public void logVariable(String name, double value) {
    logMap.computeIfAbsent(name, key -> new StatisticAccumulator());
    logMap.get(name).add(value);
  }

  /**
   * Returns an object with statistics by its name.
   *
   * @param name feature name
   * @return statistics for variable
   */
  @Override
  public StatisticAccumulator getStatisticForVariable(String name) {
    return logMap.get(name);
  }

  /**
   * Returns a named collection of statistics as a Map. Referring to the StatisticAccumulator by
   * Name.
   *
   * @return a collection of statistics
   */
  @Override
  public Map<String, StatisticAccumulator> getStatistic() {
    return logMap;
  }

  /**
   * Adds an observer for a specific variable.
   *
   * @param name     variable name
   * @param observer variable observer
   */
  @Override
  public void addObserverForVariable(String name, VariableObserver observer) {
    observers.put(name, observer);
  }

}
