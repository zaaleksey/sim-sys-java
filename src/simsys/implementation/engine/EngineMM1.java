package simsys.implementation.engine;

import simsys.api.engine.Engine;
import simsys.api.events.Event;
import simsys.implementation.environment.Environment;
import simsys.mm1.InfoMM1;
import simsys.implementation.events.ArrivalDemandsEvent;
import simsys.mm1.Queue;

import java.util.logging.Logger;

public class EngineMM1 implements Engine {

    private Logger logger = Logger.getLogger(Engine.class.getName());

    private Queue queue = new Queue();

    private Environment environment;
    private InfoMM1 infoMM1;

    public EngineMM1(Environment environment, InfoMM1 infoMM1) {
        this.environment = environment;
        this.infoMM1 = infoMM1;

        this.infoMM1.setArrivalTime(this.infoMM1.getNextArrivalValue());
    }

    @Override
    public void run(double simulationTime) {

        // планируем прибытие самого первого требования
        Event firstEvent = new ArrivalDemandsEvent(
                infoMM1.getArrivalTime(), environment, queue, infoMM1);
        // добавляем событие прибытия первого требования в контейнер
        environment.getEventContainer().addEvent(firstEvent);

        while (infoMM1.getCurrentTime() <= simulationTime) {

            Event event = environment.getEventContainer().getAndDeleteUpcomingEvent();

            if (event instanceof ArrivalDemandsEvent) {

                // arrival планирует события прихода требований в систему
                infoMM1.setArrivalTime(infoMM1.getCurrentTime() + infoMM1.getNextArrivalValue());
                Event nextArrivalEvent = new ArrivalDemandsEvent(
                        infoMM1.getArrivalTime(), environment, queue, infoMM1);
                environment.getEventContainer().addEvent(nextArrivalEvent);
            }

            infoMM1.setCurrentTime(event.getActionEventTime());
            event.actionEvent();



            System.out.println("Current Time " + infoMM1.getCurrentTime() + " event type " + event.getClass().getName() );
            System.out.println(environment.getEventContainer().toString() + "\n");
        }

        infoMM1.setAveregeResponseTime(infoMM1.getAveregeResponseTime() / infoMM1.getCountOfDemands());
        System.out.println("E[t] = " + infoMM1.getAveregeResponseTime());
    }
}
