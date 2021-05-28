package simsys.component.system;

import lombok.extern.slf4j.Slf4j;
import simsys.core.annotation.Statistic;
import simsys.core.context.SimulationContext;
import simsys.entity.demand.Demand;
import simsys.entity.queue.Queue;
import simsys.random.RandomVariable;

@Slf4j
public class QueueingSystemAgent extends AbstractQueueingSystem {

  private static final String SOJOURN_TIME = "SOJOURN_TIME";
  @Statistic
  public double countOfDemands = 0;
  private Demand processingDemand;

  public QueueingSystemAgent(SimulationContext context,
      Queue queue, RandomVariable randomVariable,
      String agentName) {
    super(context, queue, randomVariable, agentName);
  }

  @Override
  public void startService() {
    if (queue.size() > 0 && processingDemand == null) {
      processingDemand = queue.remove();
      processingDemand.setServiceStartTime(context.getCurrentTime());
      performActionAfterTimeout(this::endService, serviceTimeRV.nextValue());
    }
  }

  @Override
  public void endService() {
    if (processingDemand != null) {
      processingDemand.setLeavingTime(context.getCurrentTime());
      logValue(SOJOURN_TIME, processingDemand.getLeavingTime() - processingDemand.getArrivalTime());
      processingDemand = null;

      performAction(this::startService);
    }
  }

  @Statistic
  private int getNumberDemandInSystem() {
    int processingDemandNumber = (processingDemand == null) ? 0 : 1;
    return queue.size() + processingDemandNumber;
  }
}
