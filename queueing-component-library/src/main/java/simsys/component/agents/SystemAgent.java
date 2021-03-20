package simsys.component.agents;

import java.util.Random;
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
  private final Random random;

  @Statistic
  private int numberOfDemandsInSystem;
  private final RandomVariable randomVariable;

  public SystemAgent(RandomVariable randomVariable) {
    this.numberOfDemandsInSystem = 1;
    this.randomVariable = randomVariable;

    this.random = new Random();
  }

  @Action(states = {EMPTY_STATE, BUSY_STATE})
  public void action() {
    System.out.println("System action... Current state: " + this.currentState);
    System.out.println("Number of demands in system:" + this.numberOfDemandsInSystem);
    double delay = this.randomVariable.nextValue();

//    //  option 1
//    if (this.currentState.equals(EMPTY_STATE)) {
//      if (this.numberOfDemandsInSystem == 0) {
//        // doesn't work without it
//        sleep(1);
//      } else {
//        this.currentState = BUSY_STATE;
//        this.numberOfDemandsInSystem--;
//        moveToStateAfterTimeout(defineNextState(), delay);
//      }
//    } else {
//      this.numberOfDemandsInSystem--;
//      moveToStateAfterTimeout(defineNextState(), delay);
//    }

    // option 2
    if (this.currentState.equals(EMPTY_STATE)) {
      if (this.numberOfDemandsInSystem == 0) {
        // endless loop
        moveToState(EMPTY_STATE);
      } else {
        moveToState(BUSY_STATE);
      }
    }

    if (this.currentState.equals(BUSY_STATE)) {
      this.numberOfDemandsInSystem--;
      if (this.numberOfDemandsInSystem == 0) {
        moveToStateAfterTimeout(EMPTY_STATE, delay);
      } else {
        moveToStateAfterTimeout(BUSY_STATE, delay);
      }
    }

  }

  private String defineNextState() {
    return this.numberOfDemandsInSystem == 0 ? EMPTY_STATE : BUSY_STATE;
  }

  @Override
  public void receive() {
    this.numberOfDemandsInSystem++;
    System.out.println("The system accepts the new demand. "
        + "Number of demands in system: " + this.numberOfDemandsInSystem);

    if (this.currentState.equals(EMPTY_STATE)) {
      action();
    }

  }

}
