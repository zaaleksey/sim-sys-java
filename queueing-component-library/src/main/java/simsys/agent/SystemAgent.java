package simsys.agent;

import lombok.extern.slf4j.Slf4j;
import simsys.core.agent.AbstractAgent;
import simsys.core.annotation.Action;
import simsys.core.annotation.State;
import simsys.core.context.SimulationContext;
import simsys.entity.demand.Demand;
import simsys.entity.queue.Queue;
import simsys.random.RandomVariable;

@Slf4j
public class SystemAgent extends AbstractAgent {

  @State(initial = true)
  private static final String EMPTY_STATE = "EMPTY";
  @State
  private static final String BUSY_STATE = "BUSY";

  private final Queue queue;
  private final RandomVariable randomVariable;

  public SystemAgent(SimulationContext simulationContext, Queue queue, RandomVariable randomVariable) {
    this.simulationContext = simulationContext;
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

  private void acceptDemand(Demand demand) {
    this.queue.add(demand);
    LOGGER.debug("The system accepts the new demand. " +
        "Number of demands in queue: " + this.queue.size());
  }

}
