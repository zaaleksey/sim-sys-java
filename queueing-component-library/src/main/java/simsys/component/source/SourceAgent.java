package simsys.component.source;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import simsys.core.agent.AbstractAgent;
import simsys.core.context.SimulationContext;
import simsys.entity.demand.Demand;
import simsys.entity.demand.SimpleDemand;
import simsys.random.RandomVariable;
import simsys.transfer.Receiver;

@Slf4j
@Component
public class SourceAgent extends AbstractAgent implements Source {

  private final RandomVariable interArrivalTimes;
  private Receiver receiver;

  public SourceAgent(SimulationContext simulationContext,
      RandomVariable interArrivalTimes,
      String sourceName) {
    super(sourceName);
    this.context = simulationContext;
    this.interArrivalTimes = interArrivalTimes;
  }

  @Override
  public void sendDemand() {
    Demand demand = new SimpleDemand(context.getCurrentTime());
    receiver.receive(demand);
    LOGGER.debug("Source wake up... send a demand to the system. Demand ID: {}. Current time: {}",
        demand.getId(), context.getCurrentTime());

    performActionAfterTimeout(this::sendDemand, interArrivalTimes.nextValue());
  }

  @Override
  public void setReceiver(Receiver receiver) {
    this.receiver = receiver;
  }

}
