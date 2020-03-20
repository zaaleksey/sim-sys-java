package simsys.engine.api;

import simsys.clock.api.Clock;
import simsys.environment.api.Environment;
import simsys.event_sets.api.Event;

public interface Engine {
    default void run(Environment env, Clock clock, double simulationTime) {
        while(clock.getCurrentTime() <= simulationTime) {
            // TODO: Реализовать цикл
            Event event = env.getEventManager().removeEvent();
            env.getEventManager().addListEvent(event.actionEvent());
            clock.setCurrentTime(event.getActionTime());
        }
    }
}
