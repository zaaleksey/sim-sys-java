package simsys.core.model;

import simsys.clock.Clock;
import simsys.core.environment.Environment;
import simsys.core.provider.EventProvider;

public class SimulationContextImpl implements SimulationContext {

    protected Environment environment;
    protected Clock clock;
    protected EventProvider eventProvider;

    public SimulationContextImpl(Environment environment, Clock clock, EventProvider eventProvider) {
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
}
