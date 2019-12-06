package simsys.implementation.events;

import simsys.api.events.Event;
import simsys.api.random.RandomVariable;

import java.util.logging.Logger;

public class ServicedRequirementEvent extends Event {

    Logger logger = Logger.getLogger(ServicedRequirementEvent.class.getName());

    private RandomVariable serviceTime;


    @Override
    public void actionEvent() {
        logger.info("Serviced requirements. Action time: " + actionEventTime);
        // требование начинает обслуживание
    }
}
