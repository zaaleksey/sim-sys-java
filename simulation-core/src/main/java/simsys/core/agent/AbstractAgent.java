package simsys.core.agent;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import simsys.core.context.SimulationContext;

public abstract class AbstractAgent implements Agent {

  protected SimulationContext context;

  protected Set<String> states;
  protected String currentState;
  protected String nextState;
  protected double nextActivationTime;

  @Override
  public String currentState() {
    return this.currentState;
  }

  @Override
  public Set<String> getAllStates() {
    return this.states;
  }

  @Override
  public void sleep() {
    this.nextState = this.currentState;
    this.nextActivationTime = Double.POSITIVE_INFINITY;
  }

  @Override
  public void sleep(double delay) {
    this.nextState = this.currentState;
    this.nextActivationTime = this.context.getCurrentTime() + delay;
  }

  @Override
  public void moveToState(String state) {
    this.nextState = state;
    this.nextActivationTime = this.context.getCurrentTime();
  }

  @Override
  public void moveToStateAfterTimeout(String state, double delay) {
    this.nextState = state;
    this.nextActivationTime = this.context.getCurrentTime() + delay;
  }

  @Override
  @Autowired
  public void setContext(SimulationContext context) {
    this.context = context;
  }

}
