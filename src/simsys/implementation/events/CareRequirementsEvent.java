package simsys.implementation.events;

import simsys.api.events.Event;

import java.util.logging.Logger;

public class CareRequirementsEvent extends Event {

    private Logger logger = Logger.getLogger(CareRequirementsEvent.class.getName());

    @Override
    public void actionEvent() {
        logger.info("Care requirements. Action time: " + actionEventTime);
        // требование уходит
    }
}
