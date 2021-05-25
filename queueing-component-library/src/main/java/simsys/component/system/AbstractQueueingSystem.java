package simsys.component.system;

import simsys.core.agent.AbstractAgent;
import simsys.core.context.SimulationContext;
import simsys.core.parcel.Parcel;
import simsys.entity.demand.Demand;
import simsys.entity.queue.Queue;
import simsys.random.RandomVariable;
import simsys.transfer.Receiver;

public abstract class AbstractQueueingSystem extends AbstractAgent implements QueueingSystem, Receiver {

    protected final Queue queue;
    protected final RandomVariable serviceTimeRV;

    public AbstractQueueingSystem(SimulationContext context, Queue queue,
                                  RandomVariable randomVariable, String agentName) {
        super(agentName);
        this.context = context;
        this.queue = queue;
        this.serviceTimeRV = randomVariable;
    }

    @Override
    public void receive(Parcel parcel) {
        queue.add((Demand) parcel);
        performAction(this::startService);
    }
}
