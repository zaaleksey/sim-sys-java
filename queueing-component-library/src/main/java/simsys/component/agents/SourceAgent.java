package simsys.component.agents;

import java.util.List;
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
  private List<Receiver> receivers;

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
    send();
    sleep(delay);
  }


  public void setReceivers(List<Receiver> receivers) {
    this.receivers = receivers;
  }

  @Override
  public void send() {
    receivers.forEach(Receiver::receive);
  }

}
