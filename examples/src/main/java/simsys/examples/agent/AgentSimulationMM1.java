package simsys.examples.agent;

import java.util.Arrays;
import java.util.Random;
import simsys.agent.QueueingSystemAgent;
import simsys.agent.SourceAgent;
import simsys.core.condition.TimeStopCondition;
import simsys.core.context.SimulationContext;
import simsys.core.context.SimulationContextImpl;
import simsys.core.model.AgentBasedSimulationModel;
import simsys.entity.queue.Queue;
import simsys.entity.queue.QueueFIFO;
import simsys.random.ExponentialRandomVariable;

public class AgentSimulationMM1 {

  public static void main(String[] args) {
    SimulationContext context = SimulationContextImpl.getContext();
    Random r = new Random(0);
    double lambda = 1;
    SourceAgent source = new SourceAgent(context,
        new ExponentialRandomVariable(r, lambda),
        "Source");

    double mu = 2;
    Queue queue = new QueueFIFO();
    QueueingSystemAgent queueingSystem = new QueueingSystemAgent(context, queue,
        new ExponentialRandomVariable(r, mu),
        "Server"
    );

    source.setReceiver(queueingSystem);

    AgentBasedSimulationModel agentSimulationMM1 = new AgentBasedSimulationModel(context);
    agentSimulationMM1.setStopCondition(new TimeStopCondition(10_000_000));
    agentSimulationMM1.addAgents(Arrays.asList(source, queueingSystem));
    source.sendDemand();

    long startTime = System.nanoTime();
    agentSimulationMM1.run();
    long endTime = System.nanoTime();
    long timeElapsed = endTime - startTime;
    System.out.println("Elapsed time = " + (double) (timeElapsed) / 1000_000_000);

    // correct answer  = 1/(mu - lambda) = 1
    System.out.println("Average service time is "
        + queueingSystem.getStatisticForVariable("SOJOURN_TIME").mean());
  }

}
