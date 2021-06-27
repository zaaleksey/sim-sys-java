package simsys.examples.agent;

import java.util.Arrays;
import java.util.Random;
import simsys.component.source.SourceAgent;
import simsys.core.condition.TimeStopCondition;
import simsys.core.context.SimulationContext;
import simsys.core.context.SimulationContextImpl;
import simsys.core.model.AgentBasedSimulationModel;
import simsys.random.ExponentialRandomVariable;

public class CheckAgentsByName {

  public static void main(String[] args) {
    Random r = new Random(0);
    SimulationContext context = SimulationContextImpl.getContext();
    double lambda = 1;

    SourceAgent originalSource = new SourceAgent(context,
        new ExponentialRandomVariable(r, lambda),
        "Source");

    SourceAgent imitatorSource = new SourceAgent(context,
        new ExponentialRandomVariable(r, lambda),
        "Source");


    AgentBasedSimulationModel agentSimulationMM1 = new AgentBasedSimulationModel(context);
    agentSimulationMM1.setStopCondition(new TimeStopCondition(10));
    agentSimulationMM1.addAgents(Arrays.asList(originalSource, imitatorSource));
  }
}
