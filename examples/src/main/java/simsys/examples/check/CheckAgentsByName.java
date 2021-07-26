package simsys.examples.check;

import java.util.Arrays;
import simsys.component.source.SourceAgent;
import simsys.core.condition.TimeStopCondition;
import simsys.core.context.SimulationContext;
import simsys.core.context.SimulationContextImpl;
import simsys.core.model.AgentBasedSimulationModel;
import simsys.random.ExponentialRandomVariable;

public class CheckAgentsByName {

  public static void main(String[] args) {
    SimulationContext context = SimulationContextImpl.getContext();
    double lambda = 1;

    SourceAgent originalSource = new SourceAgent(context,
        new ExponentialRandomVariable(lambda),
        "Source");

    SourceAgent imitatorSource = new SourceAgent(context,
        new ExponentialRandomVariable(lambda),
        "Source");

    AgentBasedSimulationModel agentSimulationMM1 = new AgentBasedSimulationModel(context);
    agentSimulationMM1.setStopCondition(new TimeStopCondition(10));
    agentSimulationMM1.addAgents(Arrays.asList(originalSource, imitatorSource));
  }
}
