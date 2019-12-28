package simsys.implementation.events;

import org.apache.log4j.Logger;
import simsys.api.events.Event;
import simsys.api.random.RandomVariable;
import simsys.mm1.DemandMM1;
import simsys.mm1.QueueMM1;
import simsys.mm1.StatisticsMM1;

import java.util.ArrayList;
import java.util.List;

public class CareDemandsEvent extends Event {

    Logger logger = Logger.getLogger("");

    private RandomVariable arrival;
    private RandomVariable care;

    public CareDemandsEvent(double actionEventTime,
                            RandomVariable arrival, RandomVariable care) {
        this.actionEventTime = actionEventTime;
        this.arrival = arrival;
        this.care = care;
    }

    @Override
    public List<Event> actionEvent() {

        List<Event> createEvent = new ArrayList<>();

        DemandMM1 demand = QueueMM1.pop();

        if (!QueueMM1.isEmpty()) {
            createEvent.add(new ServicedDemandEvent(actionEventTime, arrival,care));
        }

        StatisticsMM1.increment();
        StatisticsMM1.setAveregeResponseTime(actionEventTime -
                demand.getArrivingTime());

        logger.debug("Demand ID: " + demand.ID + " care");
        logger.info("|Time: " + actionEventTime + "|");

        return createEvent;
    }
}
