package simsys.examples.agent;

import simsys.core.agent.AbstractAgent;
import simsys.core.agent.Agent;
import simsys.random.ExponentialRV;
import simsys.random.RandomVariable;

import java.util.Random;

public class AgentSimulationMM1 {

    public static void main(String[] args) {

        Agent mm1Agent = new AbstractAgent() {
            private double lambda = 1;
          RandomVariable in = new ExponentialRV(new Random(), );
        };
    }
}
