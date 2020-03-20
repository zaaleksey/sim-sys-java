package simsys.event_sets.imp.old.events;

import org.apache.log4j.Logger;
import old.api.events.Event;
import simsys.event_sets.imp.old.events.CareDemandsEvent;
import simsys.random.api.RandomVariable;
import old.mm1.DemandMM1;
import old.mm1.QueueMM1;
import old.mm1.ServiceMM1;

import java.util.ArrayList;
import java.util.List;

public class ServicedDemandEvent extends Event {
    Logger logger = Logger.getLogger("");

    RandomVariable arrival;
    RandomVariable care;

    public ServicedDemandEvent(double actionEventTime,
                               RandomVariable arrival, RandomVariable care) {
        this.actionEventTime = actionEventTime;
        this.arrival = arrival;
        this.care = care;
    }

    @Override
    public List<Event> actionEvent() {

        List<Event> createEvent = new ArrayList<>();

        DemandMM1 demand = QueueMM1.getFirst();
        ServiceMM1.serviceDemand(demand, actionEventTime, care);

        createEvent.add(new CareDemandsEvent(demand.getServiceCompletionTime(),
                arrival, care));

        logger.debug("Demand ID: " + demand.ID + " start serviced");
        logger.info("|Time: " + actionEventTime + "|");

        return createEvent;
    }
}
