package simsys.implementation.engine;

import simsys.api.engine.Engine;
import simsys.implementation.environment.Environment;
import simsys.implementation.events.DemoEvent;
import simsys.api.events.Event;

public class SimulationEngine implements Engine {

    private Environment environment;

    public SimulationEngine(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void run(double simulationTime) {
        double currentTime = 0;
        environment.getEventContainer().addEvent(new DemoEvent(environment, 1));

        while (simulationTime > currentTime) {
            Event event = environment.getEventContainer().getAndDeleteUpcomingEvent();
            event.actionEvent();
            System.out.println("Current Time " + currentTime);
            currentTime = event.getActionEventTime();
        }
    }
}
