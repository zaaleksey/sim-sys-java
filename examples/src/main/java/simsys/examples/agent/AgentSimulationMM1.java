package simsys.examples.agent;

import simsys.core.agent.AbstractAgent;
import simsys.core.agent.Agent;
import simsys.core.condition.TimeStopCondition;
import simsys.core.context.SimulationContextImpl;
import simsys.core.model.AgentBasedSimulationModel;
import simsys.random.ExponentialRV;
import simsys.random.RandomVariable;

import java.util.Random;

public class AgentSimulationMM1 {

    public static void main(String[] args) {

        Agent mm1Agent = new AbstractAgent() {

          private final double lambda = 1;
          private final double mu = 2;

          RandomVariable in = new ExponentialRV(new Random(), lambda);
          RandomVariable out = new ExponentialRV(new Random(), mu);

//          определить состяния

//          функцию action для перехода из состояния в состояние

        };

      AgentBasedSimulationModel agentSimulationMM1 = new AgentBasedSimulationModel(
          SimulationContextImpl.getEmptyInstance());
      agentSimulationMM1.setStopCondition(new TimeStopCondition(100));
      agentSimulationMM1.addAgent(mm1Agent);
      agentSimulationMM1.run();

    }
}
