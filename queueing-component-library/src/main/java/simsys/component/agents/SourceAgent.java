package simsys.component.agents;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import simsys.core.agent.AbstractAgent;
import simsys.core.annotation.Action;
import simsys.core.annotation.State;
import simsys.core.context.SimulationContext;
import simsys.random.RandomVariable;

@Slf4j
@Component
public class SourceAgent extends AbstractAgent implements Sender {

  // the source has only one state
  @State(initial = true, statistic = false)
  private static final String SLEEP_STATE = "SLEEP";

  private final RandomVariable randomVariable;
  // recipient array?
  private Receiver receiver;

  @Autowired
  public SourceAgent(SimulationContext simulationContext, RandomVariable randomVariable) {
    // the time of the first activation of the source is determined
    this.context = simulationContext;
    this.randomVariable = randomVariable;
  }

  @Action(states = {SLEEP_STATE})
  public void wakeUp() {
    LOGGER.debug("Source wake up... send a demand to the system. Current time: " + this.context.getCurrentTime());
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
