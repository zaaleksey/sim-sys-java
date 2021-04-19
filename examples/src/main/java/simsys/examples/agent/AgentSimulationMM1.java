package simsys.examples.agent;

import java.util.Random;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import simsys.agent.SourceAgent;
import simsys.agent.SystemAgent;
import simsys.core.CoreConfig;
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

    double lambda = 1;
    SourceAgent source = new SourceAgent(context, new ExponentialRandomVariable(new Random(), lambda));

    double mu = 2;
    Queue queue = new QueueFIFO();
    SystemAgent system = new SystemAgent(context, queue, new ExponentialRandomVariable(new Random(), mu));

    AgentBasedSimulationModel agentSimulationMM1 = new AgentBasedSimulationModel(context);

    agentSimulationMM1.setStopCondition(new TimeStopCondition(50));

    agentSimulationMM1.addAgent(source);
    agentSimulationMM1.addAgent(system);

    agentSimulationMM1.run();

  }
}
