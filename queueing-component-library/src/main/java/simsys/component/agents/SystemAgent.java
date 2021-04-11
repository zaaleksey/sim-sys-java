package simsys.component.agents;

import lombok.extern.slf4j.Slf4j;
import simsys.core.agent.AbstractAgent;
import simsys.core.annotation.Action;
import simsys.core.annotation.State;
import simsys.core.context.SimulationContext;
import simsys.entity.demand.Demand;
import simsys.entity.demand.SimpleDemand;
import simsys.entity.queue.Queue;
import simsys.random.RandomVariable;

@Slf4j
public class SystemAgent extends AbstractAgent implements Receiver {

  @State(initial = true)
  private static final String EMPTY_STATE = "EMPTY";
  @State
  private static final String BUSY_STATE = "BUSY";

  private final Queue queue;
  private final RandomVariable randomVariable;

  public SystemAgent(SimulationContext context, Queue queue, RandomVariable randomVariable) {
    this.context = context;
    this.queue = queue;
    this.randomVariable = randomVariable;
  }

  @Action(states = {EMPTY_STATE, BUSY_STATE})
  public void action() {
    LOGGER.debug("System action... Current state: " + this.currentState);
    double delay = this.randomVariable.nextValue();

    if (this.currentState.equals(EMPTY_STATE)) {
      if (this.queue.size() == 0) {
        sleep();
      } else {
        moveToState(BUSY_STATE);
      }
    }

    if (this.currentState.equals(BUSY_STATE)) {
      this.queue.remove();
      if (this.queue.size() == 0) {
        moveToStateAfterTimeout(EMPTY_STATE, delay);
      } else {
        moveToStateAfterTimeout(BUSY_STATE, delay);
      }
    }
    LOGGER.debug("Number of demands in system:" + this.queue.size());

  }

  @Override
  public void receive() {
    Demand demand = new SimpleDemand(this.nextActivationTime);
    this.queue.add(demand);
    LOGGER.debug("The system accepts the new demand. "
        + "Number of demands in system: " + this.queue.size());

    if (this.currentState.equals(EMPTY_STATE)) {
      this.nextActivationTime = this.context.getCurrentTime();
    }

  }

}
