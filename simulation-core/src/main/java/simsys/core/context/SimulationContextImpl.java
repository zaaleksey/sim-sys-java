package simsys.core.context;

import lombok.Builder;
import simsys.core.clock.Clock;
import simsys.core.clock.ClockImpl;
import simsys.core.environment.Environment;
import simsys.core.environment.EnvironmentImpl;
import simsys.core.provider.EventProvider;
import simsys.core.provider.EventProviderImpl;

import java.util.Collections;

@Builder(toBuilder = true)
public class SimulationContextImpl implements SimulationContext {

  protected Environment environment;
  protected Clock clock;
  protected EventProvider eventProvider;

  public SimulationContextImpl(Environment environment,
      Clock clock, EventProvider eventProvider) {
    this.environment = environment;
    this.clock = clock;
    this.eventProvider = eventProvider;
  }

  @Override
  public Environment getEnvironment() {
    return environment;
  }

  @Override
  public Clock getClock() {
    return clock;
  }

  @Override
  public EventProvider getEventProvider() {
    return eventProvider;
  }

  public static SimulationContextImpl getEmptyInstance() {
    return SimulationContextImpl
            .builder()
            .environment(new EnvironmentImpl())
            .clock(new ClockImpl())
            .eventProvider(new EventProviderImpl(Collections.emptyList()))
            .build();
  }

}
