package simsys.component.agents;

import simsys.core.agent.AbstractAgent;
import simsys.core.annotation.Action;
import simsys.core.annotation.State;
import simsys.random.RandomVariable;

public class SourceAgent extends AbstractAgent implements Sender {

  // the source has only one state
  @State(initial = true, statistic = false)
  private static final String SLEEP_STATE = "SLEEP";

  private final RandomVariable randomVariable;
  // recipient array?
  private Receiver receiver;

  public SourceAgent(RandomVariable randomVariable) {
    // the time of the first activation of the source is determined
    this.nextActivationTime = randomVariable.nextValue();
    this.randomVariable = randomVariable;
  }

  @Action(states = {SLEEP_STATE})
  public void wakeUp() {
    System.out.println("Source wake up... send a demand to the system");
    double delay = this.randomVariable.nextValue();
    send(this.receiver);
    sleep(delay);
  }


  public void setReceiver(Receiver receiver) {
    this.receiver = receiver;
  }

  @Override
  public void send(Receiver receiver) {
    receiver.receive();
  }

}
