package simsys.core.context;

import java.util.Map;
import simsys.core.clock.Clock;
import simsys.core.environment.Environment;
import simsys.core.provider.EventProvider;
import symsys.statistic.StatisticAccumulator;

/**
 * An interface for implementing the context of the simulation model. Contains basic objects for
 * discrete event simulation and methods for working with them.
 */
public interface SimulationContext {

  /**
   * Returns the time between the last two events.
   *
   * @return the time between the last two events
   */
  double getDeltaTimeLastTwoEvents();

  /**
   * Sets the time between the last two events.
   *
   * @param delta the time between the last two events
   */
  void setDeltaTimeLastTwoEvents(double delta);

  /**
   * Returns a simulation model environment.
   *
   * @return a simulation model environment
   */
  Environment getEnvironment();

  /**
   * Returns the simulation clock of the model.
   *
   * @return the simulation clock
   */
  Clock getClock();

  /**
   * Returns the event provider of the simulation model.
   *
   * @return the event provider
   */
  EventProvider getEventProvider();

  /**
   * Returns the current time value of the simulation model.
   *
   * @return the current time value
   */
  default double getCurrentTime() {
    return getClock().currentTime();
  }

  /**
   * Function to update the time value between the last two events.
   */
  default void updateDeltaTimeLastTwoEvents() {
    setDeltaTimeLastTwoEvents(getClock().deltaTime());
  }

  // TODO: Можно логировать не только double, а любые абстрактные сущности

  /**
   * Logging values for collecting and calculating characteristics.
   *
   * @param name  feature name
   * @param value transmitted value for logging
   */
  void logVariable(String name, double value);

  /**
   * Returns an object with statistics by its name.
   *
   * @param name feature name
   * @return statistics for variable
   */
  StatisticAccumulator getStatisticForVariable(String name);

  /**
   * Returns a named collection of statistics as a Map. Referring to the StatisticAccumulator by
   * Name.
   *
   * @return a collection of statistics
   */
  Map<String, StatisticAccumulator> getStatistic();

  /**
   * Adds an observer for a specific variable.
   *
   * @param name     variable name
   * @param observer variable observer
   */
  void addObserverForVariable(String name, VariableObserver observer);

}
