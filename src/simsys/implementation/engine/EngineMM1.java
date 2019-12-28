package simsys.implementation.engine;

import org.apache.log4j.Logger;
import simsys.api.engine.Engine;
import simsys.api.events.Event;
import simsys.api.random.RandomVariable;
import simsys.implementation.clock.Clock;
import simsys.implementation.environment.Environment;
import simsys.implementation.events.ArrivalDemandsEvent;

public class EngineMM1 implements Engine {

    Logger logger = Logger.getLogger(Engine.class);

    private Environment environment;
    private RandomVariable arrival;
    private RandomVariable care;

    public EngineMM1(Environment environment,
                     RandomVariable arrival, RandomVariable care) {
        this.environment = environment;
        this.arrival = arrival;
        this.care = care;
    }

    @Override
    public void run(double simulationTime) {

        logger.debug("Start Simulation M/M/1. Simulation Time: " + simulationTime);

        // планируем прибытие самого первого требования
        Event firstEvent = new ArrivalDemandsEvent(
                Clock.getCurrentTime() + arrival.nextValue(),
                arrival, care);

        // добавляем событие прибытия первого требования в контейнер
        environment.getEventManager().addEvent(firstEvent);

        while (Clock.getCurrentTime() <= simulationTime) {

            Event event = environment.getEventManager().getAndDeleteUpcomingEvent();
            environment.getEventManager().addListEvent(event.actionEvent());
            Clock.setCurrentTime(event.getActionEventTime());
        }
    }
}
