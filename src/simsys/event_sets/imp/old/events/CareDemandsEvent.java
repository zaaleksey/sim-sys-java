package simsys.event_sets.imp.old.events;

import old.mm1.DemandMM1;
import old.mm1.QueueMM1;
import old.mm1.StatisticsMM1;
import org.apache.log4j.Logger;
import simsys.event_sets.api.Event;
import simsys.random.api.RandomVariable;

import java.util.ArrayList;
import java.util.List;

public class CareDemandsEvent extends Event {
    Logger logger = Logger.getLogger("");

    private RandomVariable arrival;
    private RandomVariable care;

    public CareDemandsEvent(double actionEventTime,
                            RandomVariable arrival, RandomVariable care) {
        this.actionTime = actionEventTime;
        this.arrival = arrival;
        this.care = care;
    }

    @Override
    public List<Event> actionEvent() {

        List<Event> createEvent = new ArrayList<>();

        Demand demand = Queue.pop();

        if (!Queue.isEmpty()) {
            createEvent.add(new ServicedDemandEvent(actionTime, arrival,care));
        }

        Statistics.increment();
        Statistics.setAverageResponseTime(actionTime -
                demand.getArrivingTime());

        logger.debug("Demand ID: " + demand.ID + " care");
        logger.info("|Time: " + actionTime + "|");

        return createEvent;
    }
}
