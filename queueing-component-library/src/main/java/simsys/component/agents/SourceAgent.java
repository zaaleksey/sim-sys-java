package simsys.component.agents;

import simsys.core.agent.AbstractAgent;
import simsys.core.annotation.Action;
import simsys.core.annotation.State;
import simsys.random.RandomVariable;

public class SourceAgent extends AbstractAgent implements Sender {

  @State(initial=true, statistic = false)
  private static final String SLEEP_STATE = "SLEEP";  //  the source has only one state

  private final RandomVariable randomVariable;
  //  recipient array?
  private Receiver receiver;

  public SourceAgent(RandomVariable randomVariable) {
    this.randomVariable = randomVariable;
  }

  @Action(states = {SLEEP_STATE})
  public void wakeUp() {
    System.out.println("Source wake up...");
    double delay = this.randomVariable.nextValue();
    send(this.receiver);
    sleep(delay);
  }


  @Override
  public void send(Receiver receiver) {
    receiver.receive();
  }

  public void setReceiver(Receiver receiver) {
    this.receiver = receiver;
  }

}
