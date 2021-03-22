package simsys.core.context;

import java.util.Collections;
import simsys.core.clock.Clock;
import simsys.core.clock.ClockImpl;
import simsys.core.environment.Environment;
import simsys.core.environment.EnvironmentImpl;
import simsys.core.provider.EventProvider;
import simsys.core.provider.EventProviderImpl;

public class SimulationContextImpl implements SimulationContext {

  private double deltaTimeLastTwoEvents;

  protected Environment environment;
  protected Clock clock;
  protected EventProvider eventProvider;

  public SimulationContextImpl(
      Environment environment,
      Clock clock,
      EventProvider eventProvider) {
    this.deltaTimeLastTwoEvents = 0;
    this.environment = environment;
    this.clock = clock;
    this.eventProvider = eventProvider;
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

  public static SimulationContextImpl getEmptyInstance() {
    return new SimulationContextImpl(
        new EnvironmentImpl(),
        new ClockImpl(),
        new EventProviderImpl(Collections.emptyList()));
  }

}
