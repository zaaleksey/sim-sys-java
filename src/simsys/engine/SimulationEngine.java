package simsys.engine;

import simsys.environment.Environment;
import simsys.demo.DemoEvent;
import simsys.events.Event;

public class SimulationEngine {

    private Environment environment;

    public SimulationEngine() {
        this(new Environment());
    }
    public SimulationEngine(Environment environment) {
        this.environment = environment;
    }

    public void run() {
        double currentTime = 0D;
        double maxSimulationTime = 10000D;
        environment.getEventContainer().addEvent(new DemoEvent(environment, 1));

        while (maxSimulationTime > currentTime) {
            Event event = (Event) environment.getEventContainer().getAndDeleteFirstEvent();
            event.actionEvent();
            System.out.println("Current Time " + currentTime);
            currentTime = event.getActionEventTime();
        }
    }
}
