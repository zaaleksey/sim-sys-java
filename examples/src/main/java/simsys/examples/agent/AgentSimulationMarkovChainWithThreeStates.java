package simsys.examples.agent;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import simsys.core.agent.AbstractAgent;
import simsys.core.agent.Agent;
import simsys.core.annotation.State;
import simsys.core.annotation.Statistic;
import simsys.core.context.SimulationContext;
import simsys.core.context.SimulationContextImpl;
import simsys.core.model.AgentBasedSimulationModel;
import simsys.random.ExponentialRandomVariable;
import simsys.random.RandomVariable;

/**
 * In this example, we consider a continuous Markov chain consisting of three states: A, B and C.
 * Initial state: A. Transition rates: q(A, B), q(A, C) = alpha (exponential); q(B, A), q(B, C) =
 * beta (exponential); q(C, A), q(C, B) = gamma (exponential).
 */
public class AgentSimulationMarkovChainWithThreeStates {

  public static void main(String[] args) {

    Agent markovAgent = new AbstractAgent("Simple Agent") {

      final SecureRandom rnd = new SecureRandom();
      final RandomVariable alpha = new ExponentialRandomVariable(1);
      final RandomVariable beta = new ExponentialRandomVariable(2);
      final RandomVariable gamma = new ExponentialRandomVariable(3);

      @Statistic
      @State(initial = true)
      private static final String MOVE_FROM_STATE_A = "A";
      @State
      private static final String MOVE_FROM_STATE_B = "B";
      @State
      private static final String MOVE_FROM_STATE_C = "C";

      private void defineAndMoveToNextState(double delay) {
        ArrayList<String> states = new ArrayList<>(Arrays.asList(MOVE_FROM_STATE_A,
            MOVE_FROM_STATE_B, MOVE_FROM_STATE_C));

        int nextState = rnd.nextInt(states.size());

        switch (states.get(nextState)) {
          case MOVE_FROM_STATE_A:
            performActionAfterTimeout(this::actionForStateA, delay);
            break;
          case MOVE_FROM_STATE_B:
            performActionAfterTimeout(this::actionForStateB, delay);
            break;
          case MOVE_FROM_STATE_C:
            performActionAfterTimeout(this::actionForStateC, delay);
            break;
          default:

        }

      }

      // One function = one state (only for demo purposes)
      public void actionForStateA() {
        defineAndMoveToNextState(alpha.nextValue());
      }

      public void actionForStateB() {
        defineAndMoveToNextState(beta.nextValue());
      }

      public void actionForStateC() {
        defineAndMoveToNextState(gamma.nextValue());
      }
    };

    SimulationContext context = SimulationContextImpl.getContext();

    markovAgent.setContext(context);

    double simulationDuration = 10_000_000;
    AgentBasedSimulationModel simulation = new AgentBasedSimulationModel(simulationDuration,
        context);

    simulation.addAgent(markovAgent);
    simulation.run();
  }

}
