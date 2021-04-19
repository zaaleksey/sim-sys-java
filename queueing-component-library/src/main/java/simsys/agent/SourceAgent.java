package simsys.agent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import simsys.core.agent.AbstractAgent;
import simsys.core.annotation.Action;
import simsys.core.annotation.State;
import simsys.core.annotation.Trigger;
import simsys.core.context.SimulationContext;
import simsys.entity.demand.Demand;
import simsys.entity.demand.SimpleDemand;
import simsys.random.RandomVariable;

@Slf4j
@Component
public class SourceAgent extends AbstractAgent {

  private final RandomVariable randomVariable;

  @State(initial = true, statistic = false)
  private static final String SLEEP_STATE = "SLEEP";

  public SourceAgent(SimulationContext simulationContext, RandomVariable randomVariable) {
    this.simulationContext = simulationContext;
    this.randomVariable = randomVariable;
  }

  @Action(states = {SLEEP_STATE})
  @Trigger(clazz = {SystemAgent.class}, methodName = "acceptDemand")
  public Demand sendDemand() {
    Demand demand = new SimpleDemand(this.simulationContext.getCurrentTime());
    double delay = this.randomVariable.nextValue();
    LOGGER.debug("Source wake up... send a demand to the system. Demand ID: " +
        demand.getId() + ". Current time: " + this.simulationContext.getCurrentTime());
    sleep(delay);
    return demand;
  }

}
