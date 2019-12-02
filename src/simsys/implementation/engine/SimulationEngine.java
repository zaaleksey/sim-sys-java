package simsys.implementation.engine;

import simsys.implementation.environment.Environment;
import simsys.implementation.events.DemoEvent;
import simsys.api.events.Event;

public class SimulationEngine {

    private Environment environment;

    public SimulationEngine(Environment environment) {
        this.environment = environment;
    }

    public void run() {
        double currentTime = 0D;
        double maxSimulationTime = 10000D;
        environment.getEventContainer().addEvent(new DemoEvent(environment, 1));

        while (maxSimulationTime > currentTime) {
            Event event = environment.getEventContainer().getAndDeleteUpcomingEvent();
            event.actionEvent();
            System.out.println("Current Time " + currentTime);
            currentTime = event.getActionEventTime();
        }
    }
}
