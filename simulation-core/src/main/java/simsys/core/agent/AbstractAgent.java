package simsys.core.agent;

import java.util.Set;

public abstract class AbstractAgent implements Agent {

  protected Set<String> states;
  protected String currentState;
  protected String nextState;
  protected double nextActivationTime;


  @Override
  public String currentState() {
    return this.currentState;
  }

  @Override
  public void sleep(double delay) {
    this.nextActivationTime += delay;
  }

  @Override
  public void moveToState(String state) {
    this.nextState = state;
  }

  @Override
  public void moveToStateAfterTimeout(String state, double delay) {
    this.nextActivationTime += delay;
    this.nextState = state;
  }

}
