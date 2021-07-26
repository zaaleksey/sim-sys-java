package simsys.component.system.server;

import simsys.core.agent.AbstractAgent;
import simsys.core.context.SimulationContext;
import simsys.entity.demand.Demand;
import simsys.entity.queue.Queue;
import simsys.random.RandomVariable;

public class Server extends AbstractAgent {

  private static final String SOJOURN_TIME = "SOJOURN_TIME";

  private final Queue queue;
  private final RandomVariable serviceTimeRV;
  private Demand processingDemand;

  public Server(SimulationContext context, Queue queue, RandomVariable randomVariable,
      String name) {
    super(name);
    this.context = context;
    this.queue = queue;
    this.serviceTimeRV = randomVariable;
  }

  public void startService() {
    if (queue.size() > 0 && processingDemand == null) {
      processingDemand = queue.remove();
      processingDemand.setServiceStartTime(context.getCurrentTime());
      performActionAfterTimeout(this::endService, serviceTimeRV.nextValue());
    }
  }

  public void endService() {
    if (processingDemand != null) {
      processingDemand.setLeavingTime(context.getCurrentTime());
      logValue(SOJOURN_TIME, processingDemand.getLeavingTime() - processingDemand.getArrivalTime());
      processingDemand = null;

      performAction(this::startService);
    }
  }

  public boolean isBusy() {
    return processingDemand != null;
  }
}
