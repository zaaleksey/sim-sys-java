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
    double simulationDuration = 10_000_000;
    SimulationContext context = SimulationContextImpl.getContext();
    double lambda = 1;

    SourceAgent originalSource = new SourceAgent(context,
        new ExponentialRandomVariable(lambda),
        "Source");

    SourceAgent imitatorSource = new SourceAgent(context,
        new ExponentialRandomVariable(lambda),
        "Source");

    AgentBasedSimulationModel agentSimulationMM1 = new AgentBasedSimulationModel(simulationDuration,
        context);
    agentSimulationMM1.addAgents(Arrays.asList(originalSource, imitatorSource));
  }
}
