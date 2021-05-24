package simsys.core.context;

import java.util.Map;
import simsys.core.clock.Clock;
import simsys.core.environment.Environment;
import simsys.core.provider.EventProvider;
import symsys.statistic.StatisticAccumulator;

public interface SimulationContext {

  double getDeltaTimeLastTwoEvents();

  void setDeltaTimeLastTwoEvents(double delta);

  Environment getEnvironment();

  Clock getClock();

  EventProvider getEventProvider();

  default double getCurrentTime() {
    return getClock().currentTime();
  }

  default void updateDeltaTimeLastTwoEvents() {
    setDeltaTimeLastTwoEvents(getCurrentTime() - getClock().previousTime());
  }

  // TODO: Можно логировать не только double, а любые абстрактные сущности
  void logVariable(String name, double value);

  StatisticAccumulator getStatisticForVariable(String name);

  Map<String, StatisticAccumulator> getStatistic();

  void addObserverForVariable(String name, VariableObserver observer);

}
