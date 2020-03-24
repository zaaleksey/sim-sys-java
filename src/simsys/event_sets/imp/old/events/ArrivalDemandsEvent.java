package simsys.event_sets.imp.old.events;

import old.mm1.DemandMM1;
import old.mm1.QueueMM1;
import old.mm1.SourceMM1;
import org.apache.log4j.Logger;
import simsys.event_sets.api.Event;
import simsys.random.api.RandomVariable;

import java.util.ArrayList;
import java.util.List;

public class ArrivalDemandsEvent extends Event {
    Logger logger = Logger.getLogger("");

    private RandomVariable arrival;
    private RandomVariable care;

    public ArrivalDemandsEvent(double actionEventTime,
                               RandomVariable arrival, RandomVariable care) {
        this.actionTime = actionEventTime;
        this.arrival = arrival;
        this.care = care;
    }

    @Override
    public List<Event> actionEvent() {

        List<Event> createEvent = new ArrayList<>();

        // планируем новое событие прихода
        createEvent.add(new ArrivalDemandsEvent(actionTime +
                arrival.nextValue(), arrival, care));

        // поступление требования
        Demand demand = Source.createDemand(actionTime);
        if (Queue.isEmpty()) {
            createEvent.add(new ServicedDemandEvent(
                    demand.getArrivingTime(), arrival, care));
        }

        Queue.push(demand);

        logger.debug("Demand ID: " + demand.ID + " arrival");
        logger.info("|Time: " + actionTime + "|");

        return createEvent;
    }
}
