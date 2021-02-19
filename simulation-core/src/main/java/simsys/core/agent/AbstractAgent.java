package simsys.core.agent;

import java.util.Set;

public abstract class AbstractAgent implements Agent {

  protected Set<String> states;
  protected String currentState;
  protected String nextState;
  protected double nextActivationTime;


  @Override
  public String currentState() {
    return currentState;
  }

  @Override
  public void sleep(double delay) {
    nextActivationTime += delay;
  }

  @Override
  public void moveToState(String state) {
    nextState = state;
  }

  @Override
  public void moveToStateAfterTimeout(String state, double delay) {
    nextActivationTime += delay;
    nextState = state;
  }

}
