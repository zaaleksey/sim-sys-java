package simsys.examples.agent;

import java.util.Arrays;
import simsys.component.source.SourceAgent;
import simsys.component.system.QueueingSystemAgent;
import simsys.core.context.SimulationContext;
import simsys.core.context.SimulationContextImpl;
import simsys.core.model.AgentBasedSimulationModel;
import simsys.entity.queue.Queue;
import simsys.entity.queue.QueueFIFO;
import simsys.random.ExponentialRandomVariable;

/**
 * This is an example of a Queuing System type MM1. Arrivals occur at rate λ according to a Poisson
 * process. Service times are exponentially distributed with rate parameter μ so that 1/μ is the
 * mean service time. A single server serves customers one at a time from the front of the queue,
 * according to a first-come, first-served discipline. When the service is complete the customer
 * leaves the queue and the number of customers in the system reduces by one.
 * <p>
 * Implementation through agent-based modeling.
 */
public class AgentSimulationMM1 {

  public static void main(String[] args) {
    double simulationDuration = 10_000_000;
    SimulationContext context = SimulationContextImpl.getContext();
    double lambda = 1;
    SourceAgent source = new SourceAgent(context,
        new ExponentialRandomVariable(lambda),
        "Source");

    double mu = 2;
    Queue queue = new QueueFIFO();
    QueueingSystemAgent queueingSystem = new QueueingSystemAgent(context, queue,
        new ExponentialRandomVariable(mu),
        "Server"
    );

    source.setReceiver(queueingSystem);

    AgentBasedSimulationModel agentSimulationMM1 = new AgentBasedSimulationModel(simulationDuration,
        context);
    agentSimulationMM1.addAgents(Arrays.asList(source, queueingSystem));
    source.sendDemand();

    long startTime = System.nanoTime();
    agentSimulationMM1.run();
    long endTime = System.nanoTime();
    long timeElapsed = endTime - startTime;
    System.out.println("Elapsed time = " + (double) (timeElapsed) / 1_000_000_000);

    // correct answer  = 1/(mu - lambda) = 1
    System.out.println("Average service time is "
        + queueingSystem.getStatisticForVariable("SOJOURN_TIME").mean());
  }

}
