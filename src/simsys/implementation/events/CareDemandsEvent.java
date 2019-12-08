package simsys.implementation.events;

import simsys.api.events.Event;
import simsys.implementation.environment.Environment;
import simsys.mm1.InfoMM1;
import simsys.mm1.Demand;
import simsys.mm1.Queue;

import java.util.logging.Logger;

public class CareDemandsEvent extends Event {

    private Logger logger = Logger.getLogger(CareDemandsEvent.class.getName());

    private Environment environment;
    private Queue queue;
    private InfoMM1 infoMM1;

    public CareDemandsEvent(double actionEventTime, Environment environment, Queue queue,
                            InfoMM1 infoMM1) {
        this.actionEventTime = actionEventTime;
        this.environment = environment;
        this.queue = queue;
        this.infoMM1 = infoMM1;
    }

    @Override
    public void actionEvent() {

        Demand demand = queue.pop();
        logger.info("Demand ID: "+ demand.ID + " care! Time : " + actionEventTime);

        // статистика M/M/1
        infoMM1.setAveregeResponseTime(infoMM1.getAveregeResponseTime() + (infoMM1.getCurrentTime() - demand.getArrivingTime()));
        infoMM1.increment();
    }
}
