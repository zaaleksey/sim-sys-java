package simsys.core.context;


import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import simsys.core.CoreConfig;
import simsys.core.clock.Clock;
import simsys.core.environment.Environment;
import simsys.core.provider.EventProvider;
import symsys.statistic.StatisticAccumulator;

public class SimulationContextImpl implements SimulationContext {

  @Autowired
  protected Environment environment;
  @Autowired
  protected Clock clock;
  @Autowired
  protected EventProvider eventProvider;
  protected Map<String, StatisticAccumulator> logMap;

  // TODO: так можно счтать средние характеристики - среднее число требований
  protected Map<String, VariableObserver> observers;
  protected Map<String, Double> observedVariablesMean;

  private double deltaTimeLastTwoEvents;

  public SimulationContextImpl() {
    deltaTimeLastTwoEvents = 0;
    logMap = new HashMap<>();
  }


  public static SimulationContext getContext() {
    return new AnnotationConfigApplicationContext(CoreConfig.class)
        .getBean(SimulationContext.class);
  }

  @Override
  public double getDeltaTimeLastTwoEvents() {
    return this.deltaTimeLastTwoEvents;
  }

  @Override
  public void setDeltaTimeLastTwoEvents(double delta) {
    this.deltaTimeLastTwoEvents = delta;
  }

  @Override
  public Environment getEnvironment() {
    return this.environment;
  }

  @Override
  public Clock getClock() {
    return this.clock;
  }

  @Override
  public EventProvider getEventProvider() {
    return this.eventProvider;
  }

  @Override
  public void logVariable(String name, double value) {
    if (!logMap.containsKey(name)) {
      logMap.put(name, new StatisticAccumulator());
    }
    logMap.get(name).add(value);
  }

  @Override
  public StatisticAccumulator getStatisticForVariable(String name) {
    return logMap.get(name);
  }

  public Map<String, StatisticAccumulator> getStatistic() {
    return logMap;
  }

  @Override
  public void addObserverForVariable(String name, VariableObserver observer) {
    observers.put(name, observer);
  }


}
