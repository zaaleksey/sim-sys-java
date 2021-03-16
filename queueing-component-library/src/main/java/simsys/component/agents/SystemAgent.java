package simsys.component.agents;

import simsys.core.agent.AbstractAgent;
import simsys.core.annotation.Action;
import simsys.core.annotation.State;
import simsys.core.annotation.Statistic;
import simsys.random.RandomVariable;

public class SystemAgent extends AbstractAgent implements Receiver {

  @State(initial = true)
  private static final String EMPTY_STATE = "EMPTY";
  @State
  private static final String BUSY_STATE = "BUSY";

  @Statistic
  private int numberOfDemandsInQueue;
  private final RandomVariable randomVariable;

  public SystemAgent(RandomVariable randomVariable) {
    this.numberOfDemandsInQueue = 0;
    this.randomVariable = randomVariable;
  }

  @Action(states = {EMPTY_STATE, BUSY_STATE})
  public void action() {
    System.out.println("System action... Current state: " + this.currentState);
    if (this.numberOfDemandsInQueue == 0) {
      moveToState(EMPTY_STATE);
    }

    if (currentState.equals(EMPTY_STATE) && this.numberOfDemandsInQueue > 0) {
      moveToState(BUSY_STATE);
    }

    if (this.currentState.equals(BUSY_STATE)) {
      this.numberOfDemandsInQueue--;
      double delay = this.randomVariable.nextValue();
      moveToStateAfterTimeout(defineNextState(), delay);
    }
  }

  private String defineNextState() {
    return this.numberOfDemandsInQueue - 1 == 0 ? EMPTY_STATE : BUSY_STATE;
  }

  @Override
  public void receive() {
    this.numberOfDemandsInQueue++;
    System.out.println("The system accepts the new demand. "
        + "Number of demands in queue: " + this.numberOfDemandsInQueue);
    if (this.currentState.equals(EMPTY_STATE)) {
      action();
    }
  }

}
