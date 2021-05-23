package simsys.examples.agent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import simsys.core.agent.AbstractAgent;
import simsys.core.agent.Agent;
import simsys.core.annotation.State;
import simsys.core.annotation.Statistic;
import simsys.core.condition.TimeStopCondition;
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
      private final Random random = new Random();

      final RandomVariable alpha = new ExponentialRandomVariable(random, 1);
      final RandomVariable beta = new ExponentialRandomVariable(random, 2);
      final RandomVariable gamma = new ExponentialRandomVariable(random, 3);

      @Statistic
      @State(initial = true)
      private final String MOVE_FROM_STATE_A = "A";
      @State
      private final String MOVE_FROM_STATE_B = "B";
      @State
      private final String MOVE_FROM_STATE_C = "C";

      private void defineAndMoveToNextState(double delay) {
        ArrayList<String> states = new ArrayList<>(Arrays.asList(MOVE_FROM_STATE_A,
            MOVE_FROM_STATE_B, MOVE_FROM_STATE_C));
        //states.remove(currentState());

        int nextState = random.nextInt(3);
        //System.out.println("Current State --> " + currentState() + ". Next state --> " + nextState);

        performActionAfterTimeout(this::actionForStateA, delay);
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

    AgentBasedSimulationModel simulation = new AgentBasedSimulationModel(context);
    simulation.setStopCondition(new TimeStopCondition(10000));

    simulation.addAgent(markovAgent);
    simulation.run();
  }

}
