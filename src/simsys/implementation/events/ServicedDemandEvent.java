package simsys.implementation.events;

import simsys.api.events.Event;
import simsys.implementation.environment.Environment;
import simsys.mm1.InfoMM1;
import simsys.mm1.Queue;

import java.util.logging.Logger;

public class ServicedDemandEvent extends Event {

    private Logger logger = Logger.getLogger(ServicedDemandEvent.class.getName());

    private Environment environment;
    private Queue queue;
    private InfoMM1 infoMM1;

    public ServicedDemandEvent(
            double actionEventTime, Environment environment, Queue queue,
            InfoMM1 infoMM1) {
        this.actionEventTime = actionEventTime;
        this.environment = environment;
        this.queue = queue;
        this.infoMM1 = infoMM1;
    }

    @Override
    public void actionEvent() {

        logger.info("Demand ID: " + queue.getFirst().ID +
                " start services! Time : " + actionEventTime);

        // планируем уод требования из системы
        Event careEvent = new CareDemandsEvent(
                queue.getFirst().getServiceCompletionTime(), environment, queue, infoMM1);
        environment.getEventContainer().addEvent(careEvent);
    }
}
