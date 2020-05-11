package simsys.core.context;

import simsys.core.clock.Clock;
import simsys.core.environment.Environment;
import simsys.core.provider.EventProvider;

public interface SimulationContext {
    Environment getEnvironment();

    Clock getClock();

    EventProvider getEventProvider();

    default double getCurrentTime() {
        return getClock().getCurrentTime();
    }
}
