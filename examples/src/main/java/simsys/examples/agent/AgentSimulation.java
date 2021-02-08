package simsys.examples.agent;

import java.util.Collections;
import java.util.Random;
import simsys.core.agent.AbstractAgent;
import simsys.core.agent.Agent;
import simsys.core.annotation.Action;
import simsys.core.annotation.State;
import simsys.core.annotation.Statistic;
import simsys.core.clock.Clock;
import simsys.core.clock.ClockImpl;
import simsys.core.condition.TimeStopCondition;
import simsys.core.context.SimulationContextImpl;
import simsys.core.context.SimulationContext;
import simsys.core.environment.Environment;
import simsys.core.environment.EnvironmentImpl;
import simsys.core.model.AgentBasedSimulationModel;
import simsys.core.provider.EventProvider;
import simsys.core.provider.EventProviderImpl;
import simsys.random.ExponentialRV;
import simsys.random.RandomVariable;

public class AgentSimulation {

  // In this example, we consider a continuous Markov chain consisting from two states: A, B.
  // Transition rates:
  // q(A, B) = alpha
  // q(B, A) = betta
  public static void main(String[] args) {

    Agent markovAgent = new AbstractAgent() {
      RandomVariable alpha = new ExponentialRV(new Random(), 0.1);
      RandomVariable beta = new ExponentialRV(new Random(), 2);

      @State(initial = true)
      @Statistic
      private final String STATE_A = "A";
      @State
      @Statistic
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

    // Должна быть фабрика, которая создает это все
    Environment env = new EnvironmentImpl();
    Clock clock = new ClockImpl();
    EventProvider eventProvider = new EventProviderImpl(Collections.emptyList());
    SimulationContext simulationContext = new SimulationContextImpl(env, clock, eventProvider);

    AgentBasedSimulationModel simulation = new AgentBasedSimulationModel(simulationContext);
    simulation.setStopCondition(new TimeStopCondition(100));

    simulation.addAgent(markovAgent);
    simulation.run();

  }
}
