package simsys.agent;

import lombok.extern.slf4j.Slf4j;
import simsys.core.agent.AbstractAgent;
import simsys.core.annotation.Action;
import simsys.core.annotation.State;
import simsys.core.annotation.Statistic;
import simsys.core.context.SimulationContext;
import simsys.entity.demand.Demand;
import simsys.entity.queue.Queue;
import simsys.random.RandomVariable;

@Slf4j
public class SystemAgent extends AbstractAgent {

  private final Queue queue;
  private final RandomVariable randomVariable;

  @State(initial = true)
  private static final String EMPTY_STATE = "EMPTY";
  @State
  private static final String BUSY_STATE = "BUSY";

  private Demand processingDemand;
  // annotation processing Statistics are not working yet, we count with our hands
  @Statistic
  public double averageServiceTime = 0;
  // annotation processing Statistics are not working yet, we count with our hands
  @Statistic
  public double countOfDemands = 0;

  public SystemAgent(SimulationContext simulationContext, Queue queue, RandomVariable randomVariable) {
    this.simulationContext = simulationContext;
    this.queue = queue;
    this.randomVariable = randomVariable;
  }

  @Action(states = {EMPTY_STATE, BUSY_STATE})
  public void action() {
    double delay = this.randomVariable.nextValue();

    if (this.processingDemand != null) {
      this.processingDemand.setLeavingTime(this.simulationContext.getCurrentTime());
      this.countOfDemands++;
      this.averageServiceTime += this.processingDemand.getLeavingTime() - this.processingDemand.getArrivalTime();
      this.processingDemand = null;
    }

    if (this.currentState.equals(EMPTY_STATE)) {
      if (this.queue.size() == 0) {
        sleep();
      } else {
        moveToState(BUSY_STATE);
      }
    }

    if (this.currentState.equals(BUSY_STATE)) {
      //move the demand to sever
      this.processingDemand = queue.remove();
      this.processingDemand.setServiceStartTime(this.simulationContext.getCurrentTime());

      if (this.queue.size() == 0) {
        moveToStateAfterTimeout(EMPTY_STATE, delay);
      } else {
        moveToStateAfterTimeout(BUSY_STATE, delay);
      }
    }
  }

  private void acceptDemand(Demand demand) {
    this.queue.add(demand);
    LOGGER.debug("The system accepts the new demand with ID: " + demand.getId() +
        ". Number of demands in queue: " + this.queue.size());
  }

  // annotation processing Statistics are not working yet, we count with our hands
  @Statistic
  private int getNumberDemandInSystem() {
    return this.queue.size() + 1;
  }

}
