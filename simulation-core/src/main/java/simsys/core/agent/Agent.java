package simsys.core.agent;

import simsys.core.context.SimulationContext;

import java.util.Set;

public interface Agent {

  String currentState();

  Set<String> getAllStates();

  void sleep();

  void sleep(double delay);

  void moveToState(String state);

  void moveToStateAfterTimeout(String state, double delay);

  void setContext(SimulationContext context);
}
