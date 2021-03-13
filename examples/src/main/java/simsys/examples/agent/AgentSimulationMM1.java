package simsys.examples.agent;

import simsys.core.agent.AbstractAgent;
import simsys.core.agent.Agent;
import simsys.core.annotation.Action;
import simsys.core.annotation.State;
import simsys.core.condition.TimeStopCondition;
import simsys.core.context.SimulationContextImpl;
import simsys.core.model.AgentBasedSimulationModel;
import simsys.random.ExponentialRV;
import simsys.random.RandomVariable;

import java.util.Random;

public class AgentSimulationMM1 {

  public static void main(String[] args) {
    Agent source = new AbstractAgent() {
      private final double lambda = 1;

      RandomVariable in = new ExponentialRV(new Random(), lambda);

      @State
      private final String sleepState = "sleep";

      @Action(states = {sleepState})
      void sleep() {
        sendInSystem();

      }

      private void sendInSystem() {
      }
    };

    AgentBasedSimulationModel agentSimulationMM1 = new AgentBasedSimulationModel(
        SimulationContextImpl.getEmptyInstance());
    agentSimulationMM1.setStopCondition(new TimeStopCondition(100));
    agentSimulationMM1.addAgent(source);
    agentSimulationMM1.run();

  }
}
