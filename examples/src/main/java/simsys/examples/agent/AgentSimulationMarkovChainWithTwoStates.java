package simsys.examples.agent;

import java.util.Random;
import simsys.core.agent.AbstractAgent;
import simsys.core.agent.Agent;
import simsys.core.annotation.Action;
import simsys.core.annotation.State;
import simsys.core.condition.TimeStopCondition;
import simsys.core.context.SimulationContext;
import simsys.core.context.SimulationContextImpl;
import simsys.core.model.AgentBasedSimulationModel;
import simsys.random.ExponentialRandomVariable;
import simsys.random.RandomVariable;

public class AgentSimulationMarkovChainWithTwoStates {

  /**
   *    In this example, we consider a continuous Markov chain consisting of two states: A and B
   *    Initial state: A.
   *    Transition rates:
   *    q(A, B), q(A, C) = alpha (exponential)
   *    q(B, A), q(B, C) = beta (exponential)
   */
  public static void main(String[] args) {

    Agent markovAgent = new AbstractAgent() {
      final RandomVariable alpha = new ExponentialRandomVariable(new Random(), 1);
      final RandomVariable beta = new ExponentialRandomVariable(new Random(), 2);

      @State(initial = true)
      private final String STATE_A = "A";
      @State
      private final String STATE_B = "B";

      public String defineNextState() {
        return currentState.equals(STATE_A) ? STATE_B : STATE_A;
      }

      @Action(states = {STATE_A, STATE_B})
      public void action() {
        System.out.println("Current State " + currentState);
        double delay = currentState.equals(STATE_A) ? alpha.nextValue() : beta.nextValue();
        moveToStateAfterTimeout(defineNextState(), delay);
      }
    };

    SimulationContext context = SimulationContextImpl.getContext()
    markovAgent.setContext(context);

    AgentBasedSimulationModel simulation = new AgentBasedSimulationModel(context);
    simulation.setStopCondition(new TimeStopCondition(10000));

    simulation.addAgent(markovAgent);
    simulation.run();
  }

}