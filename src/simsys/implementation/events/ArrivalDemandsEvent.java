package simsys.implementation.events;

import org.apache.log4j.Logger;
import simsys.api.events.Event;
import simsys.api.random.RandomVariable;
import simsys.mm1.DemandMM1;
import simsys.mm1.QueueMM1;
import simsys.mm1.SourceMM1;

import java.util.ArrayList;
import java.util.List;

public class ArrivalDemandsEvent extends Event {

    Logger logger = Logger.getLogger("");

    private RandomVariable arrival;
    private RandomVariable care;

    public ArrivalDemandsEvent(double actionEventTime,
                               RandomVariable arrival, RandomVariable care) {
        this.actionEventTime = actionEventTime;
        this.arrival = arrival;
        this.care = care;
    }

    @Override
    public List<Event> actionEvent() {

        List<Event> createEvent = new ArrayList<>();

        // планируем новое событие прихода
        createEvent.add(new ArrivalDemandsEvent(actionEventTime +
                arrival.nextValue(), arrival, care));

        // поступление требования
        DemandMM1 demand = SourceMM1.createDemand(actionEventTime);
        if (QueueMM1.isEmpty()) {
            createEvent.add(new ServicedDemandEvent(
                    demand.getArrivingTime(), arrival, care));
        }

        QueueMM1.push(demand);

        logger.debug("Demand ID: " + demand.ID + " arrival");
        logger.info("|Time: " + actionEventTime + "|");

        return createEvent;
    }
}
