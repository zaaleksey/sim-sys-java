package simsys.core.context;

import simsys.core.clock.Clock;
import simsys.core.environment.Environment;
import simsys.core.provider.EventProvider;

public interface SimulationContext {

  double getDeltaTimeLastTwoEvents();

  void setDeltaTimeLastTwoEvents(double delta);

  Environment getEnvironment();

  Clock getClock();

  EventProvider getEventProvider();

  default double getCurrentTime() {
    return getClock().getCurrentTime();
  }

  default void updateDeltaTimeLastTwoEvents() {
    setDeltaTimeLastTwoEvents(getCurrentTime() - getClock().getPreviousTime());
  }

}
