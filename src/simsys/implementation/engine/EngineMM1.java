package simsys.implementation.engine;

import simsys.api.engine.Engine;
import simsys.api.events.Event;
import simsys.api.random.RandomVariable;
import simsys.implementation.environment.Environment;
import simsys.implementation.events.ArrivalRequirementsEvent;

public class EngineMM1 implements Engine {

    private Environment environment;
    private RandomVariable arrival;
    private RandomVariable leave;

    private EngineMM1(Environment environment, RandomVariable arrival) {
        this.environment = environment;
        this.arrival = arrival;
    }

    @Override
    public void run(double simulationTime) {

        environment.getEventContainer().
                addEvent(ArrivalRequirementsEvent.createArrivalRequirementsEvent(1, environment.getEventContainer(), arrival));

        while (environment.getCurrentTime() <= simulationTime) {
            Event event = environment.getEventContainer().getAndDeleteUpcomingEvent();
            if (event == null) {
                break;
            }
            event.actionEvent();
            environment.setCurrentTime(event.getActionEventTime());
            System.out.println("Current Time " + environment.getCurrentTime());
        }
    }

    public static EngineMM1 createEngineMM1(Environment environment, RandomVariable arrival) {
        return new EngineMM1(environment, arrival);
    }
}
