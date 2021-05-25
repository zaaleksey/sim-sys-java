package simsys.agent;

import lombok.extern.slf4j.Slf4j;
import simsys.core.agent.AbstractAgent;
import simsys.core.annotation.Statistic;
import simsys.core.context.SimulationContext;
import simsys.core.parcel.Parcel;
import simsys.entity.demand.Demand;
import simsys.entity.queue.Queue;
import simsys.random.RandomVariable;
import simsys.transfer.Receiver;

@Slf4j
public class QueueingSystemAgent extends AbstractAgent implements Receiver {

  private static final String SOJOURN_TIME = "SOJOURN_TIME";
  private final Queue queue;
  private final RandomVariable serviceTimeRV;
  @Statistic
  public double countOfDemands = 0;
  private Demand processingDemand;

  public QueueingSystemAgent(SimulationContext simulationContext,
      Queue queue, RandomVariable randomVariable,
      String agentName) {
    super(agentName);
    this.context = simulationContext;
    this.queue = queue;
    this.serviceTimeRV = randomVariable;
  }

  public void endService() {
    if (processingDemand != null) {
      processingDemand.setLeavingTime(context.getCurrentTime());
      logValue(SOJOURN_TIME, processingDemand.getLeavingTime() - processingDemand.getArrivalTime());
      processingDemand = null;

      performAction(this::startService);
    }
  }

  public void startService() {
    if (queue.size() > 0 && processingDemand == null) {
      processingDemand = queue.remove();
      processingDemand.setServiceStartTime(context.getCurrentTime());
      performActionAfterTimeout(this::endService, serviceTimeRV.nextValue());
    }
  }

  @Statistic
  private int getNumberDemandInSystem() {
    int processingDemandNumber = (processingDemand == null) ? 0 : 1;
    return queue.size() + processingDemandNumber;
  }

  @Override
  public void receive(Parcel parcel) {
    queue.add((Demand) parcel);
    performAction(this::startService);
  }
}
