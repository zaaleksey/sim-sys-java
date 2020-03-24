package simsys.event_sets.imp.old.events;

import old.mm1.DemandMM1;
import old.mm1.QueueMM1;
import old.mm1.ServiceMM1;
import org.apache.log4j.Logger;
import simsys.event_sets.api.Event;
import simsys.random.api.RandomVariable;

import java.util.ArrayList;
import java.util.List;

public class ServicedDemandEvent extends Event {
    Logger logger = Logger.getLogger("");

    RandomVariable arrival;
    RandomVariable care;

    public ServicedDemandEvent(double actionEventTime,
                               RandomVariable arrival, RandomVariable care) {
        this.actionTime = actionEventTime;
        this.arrival = arrival;
        this.care = care;
    }

    @Override
    public List<Event> actionEvent() {

        List<Event> createEvent = new ArrayList<>();

        Demand demand = Queue.getFirst();
        Service.serviceDemand(demand, actionTime, care);

        createEvent.add(new CareDemandsEvent(demand.getServiceCompletionTime(),
                arrival, care));

        logger.debug("Demand ID: " + demand.ID + " start serviced");
        logger.info("|Time: " + actionTime + "|");

        return createEvent;
    }
}
