package simsys.examples.agent;

import java.util.Arrays;
import simsys.component.source.SourceAgent;
import simsys.component.system.QueueingSystemWithTwoServer;
import simsys.core.condition.TimeStopCondition;
import simsys.core.context.SimulationContext;
import simsys.core.context.SimulationContextImpl;
import simsys.core.model.AgentBasedSimulationModel;
import simsys.entity.queue.Queue;
import simsys.entity.queue.QueueFIFO;
import simsys.random.ExponentialRandomVariable;

public class SlowServerProblem {

  public static void main(String[] args) {
    SimulationContext context = SimulationContextImpl.getContext();
    double lambda = 3;
    SourceAgent source = new SourceAgent(context,
        new ExponentialRandomVariable(lambda),
        "Source");

    int capacity = 10;
    Queue queue = new QueueFIFO(capacity);
    QueueingSystemWithTwoServer queueing = new QueueingSystemWithTwoServer(context,
        queue, new ExponentialRandomVariable(0), "QueueingSystemWithTwoServer");

    source.setReceiver(queueing);

    AgentBasedSimulationModel agentSimulationMM1 = new AgentBasedSimulationModel(context);
    agentSimulationMM1.setStopCondition(new TimeStopCondition(10_000_000));
    agentSimulationMM1.addAgents(Arrays.asList(source, queueing));
    source.sendDemand();

    long startTime = System.nanoTime();
    agentSimulationMM1.run();
    long endTime = System.nanoTime();
    long timeElapsed = endTime - startTime;
    System.out.println("Elapsed time = " + (double) (timeElapsed) / 1_000_000_000);
    System.out.println(
        "Average service time on a FAST server is " + queueing.getServerStatistic("SOJOURN_TIME")
            .get(0).mean());
    System.out.println(
        "Average service time on a SLOW server is " + queueing.getServerStatistic("SOJOURN_TIME")
            .get(1).mean());
  }

}