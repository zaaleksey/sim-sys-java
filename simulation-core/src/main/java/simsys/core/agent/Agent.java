package simsys.core.agent;

import java.util.Set;

public interface Agent {

  String currentState();
  double getActionTime();
  Set<String> getAllStates();
  void sleep(double delay);
  void moveToState(String state);
  void moveToStateAfterTimeout(String state, double delay);

}
