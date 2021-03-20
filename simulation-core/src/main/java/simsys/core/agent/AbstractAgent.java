package simsys.core.agent;

import java.util.Set;

public abstract class AbstractAgent implements Agent {

  protected Set<String> states;
  protected String currentState;
  protected double actionTime;
  protected String nextState;
  protected double nextActivationTime;


  @Override
  public String currentState() {
    return this.currentState;
  }

  @Override
  public double getActionTime() {
    return this.actionTime;
  }

  @Override
  public Set<String> getAllStates() {
    return this.states;
  }

  @Override
  public void sleep(double delay) {
    this.nextState = currentState;
    this.nextActivationTime += delay;
  }

  @Override
  public void moveToState(String state) {
    this.nextState = state;
  }

  @Override
  public void moveToStateAfterTimeout(String state, double delay) {
    this.nextState = state;
    this.nextActivationTime += delay;
  }

}
