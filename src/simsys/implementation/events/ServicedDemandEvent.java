package simsys.implementation.events;

import org.apache.log4j.Logger;
import simsys.api.events.Event;
import simsys.api.random.RandomVariable;
import simsys.mm1.DemandMM1;
import simsys.mm1.QueueMM1;
import simsys.mm1.ServiceMM1;

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
