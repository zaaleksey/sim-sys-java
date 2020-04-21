package simsys.entity.source;

import simsys.core.environment.Entity;
import simsys.core.event.Event;
import simsys.core.model.AbstractSimulationModel;
import simsys.core.context.SimulationContext;
import simsys.entity.demand.Demand;

import java.util.Collection;

public class SourceImpl extends AbstractSimulationModel implements Source {

    @Override
    public Demand generateDemand() {
        // TODO: описать генерацию требования
        return null;
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public SimulationContext getSimulationContext() {
        return null;
    }

    @Override
    public Collection<Event> getEvents() {
        return null;
    }

    @Override
    public Collection<Entity> getEntities() {
        return null;
    }
}
