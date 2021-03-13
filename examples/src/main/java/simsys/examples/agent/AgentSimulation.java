package simsys.examples.agent;

import simsys.core.agent.AbstractAgent;
import simsys.core.agent.Agent;
import simsys.core.annotation.Action;
import simsys.core.annotation.State;
import simsys.core.annotation.Statistic;
import simsys.core.condition.TimeStopCondition;
import simsys.core.context.SimulationContextImpl;
import simsys.core.model.AgentBasedSimulationModel;
import simsys.random.ExponentialRV;
import simsys.random.RandomVariable;

import java.util.Random;


public class AgentSimulation {

  // In this example, we consider a continuous Markov chain consisting from two states: A, B.
  // Transition rates:
  // q(A, B) = alpha
  // q(B, A) = betta
  public static void main(String[] args) {

    Agent markovAgent = new AbstractAgent() {
      final RandomVariable alpha = new ExponentialRV(new Random(), 1);
      final RandomVariable beta = new ExponentialRV(new Random(), 2);

      @State(initial = true)
      private final String STATE_A = "A";
      @State
      private final String STATE_B = "B";

      public String defineNextState() {
        // there are two possibilities
        return currentState.equals(STATE_A) ? STATE_B : STATE_A;
      }

      @Action(states = {STATE_A, STATE_B})
      public void action() {
        System.out.println("Current State " + currentState);
        double delay = currentState.equals(STATE_A) ? alpha.nextValue() : beta.nextValue();
        moveToStateAfterTimeout(defineNextState(), delay);
      }
    };

    AgentBasedSimulationModel simulation = new AgentBasedSimulationModel(
        SimulationContextImpl.getEmptyInstance());
    simulation.setStopCondition(new TimeStopCondition(1500));

    simulation.addAgent(markovAgent);
    simulation.run();
  }
  
}
