package simsys.component.agents;

import lombok.extern.slf4j.Slf4j;
import simsys.core.agent.AbstractAgent;
import simsys.core.annotation.Action;
import simsys.core.annotation.State;
import simsys.core.annotation.Statistic;
import simsys.entity.demand.Demand;
import simsys.entity.demand.SimpleDemand;
import simsys.entity.queue.QueueFIFO;
import simsys.random.RandomVariable;

@Slf4j
public class SystemAgent extends AbstractAgent implements Receiver {

  @State(initial = true)
  private static final String EMPTY_STATE = "EMPTY";
  @State
  private static final String BUSY_STATE = "BUSY";

  private final QueueFIFO queue;
  private final RandomVariable randomVariable;

  public SystemAgent(RandomVariable randomVariable) {
    this.queue = new QueueFIFO();
    this.randomVariable = randomVariable;
  }

  @Action(states = {EMPTY_STATE, BUSY_STATE})
  public void action() {
    LOGGER.debug("System action... Current state: " + this.currentState);
    LOGGER.debug("Number of demands in system:" + this.queue.size());
    double delay = this.randomVariable.nextValue();

    if (this.currentState.equals(EMPTY_STATE)) {
      if (this.queue.size() == 0) {
        sleep();
      } else {
        moveToState(BUSY_STATE);
      }
    }

    if (this.currentState.equals(BUSY_STATE)) {
      this.queue.poll();
      if (this.queue.size() == 0) {
        moveToStateAfterTimeout(EMPTY_STATE, delay);
      } else {
        moveToStateAfterTimeout(BUSY_STATE, delay);
      }
    }

  }

  @Override
  public void receive() {
    Demand demand = new SimpleDemand(this.currentActivationTime);
    this.queue.add(demand);
    LOGGER.debug("The system accepts the new demand. "
        + "Number of demands in system: " + this.queue.size());

    if (this.currentState.equals(EMPTY_STATE)) {
      action();
    }

  }

}
