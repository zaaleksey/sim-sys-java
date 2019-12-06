package simsys.implementation.events;

import simsys.api.containers.EventContainer;
import simsys.api.events.Event;
import simsys.api.random.RandomVariable;

import java.util.logging.Logger;

public class ArrivalRequirementsEvent extends Event {

    private Logger logger = Logger.getLogger(ArrivalRequirementsEvent.class.getName());

    private EventContainer queueMM1;
    private RandomVariable arrival;

    private ArrivalRequirementsEvent(double actionTime, EventContainer queueMM1, RandomVariable arrival) {
        this.actionEventTime = actionTime;
        this.queueMM1 = queueMM1;
        this.arrival = arrival;
    }

    public static ArrivalRequirementsEvent createArrivalRequirementsEvent(double actionEventTime, EventContainer queueMM1, RandomVariable arrival)
    {
        return new ArrivalRequirementsEvent(actionEventTime, queueMM1, arrival);
    }

    @Override
    public void actionEvent() {
        logger.info("Arrival requirements. Action time: " + actionEventTime);
        // требование пришло
    }
}
