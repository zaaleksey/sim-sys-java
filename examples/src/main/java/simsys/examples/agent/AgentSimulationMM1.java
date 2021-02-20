package simsys.examples.agent;

import simsys.core.agent.AbstractAgent;
import simsys.core.agent.Agent;
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

          
        };
    }
}
