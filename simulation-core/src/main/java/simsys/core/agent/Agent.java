package simsys.core.agent;

public interface Agent {

  String currentState();
  void sleep(double delay);
  void moveToState(String state);
  void moveToStateAfterTimeout(String state, double delay);

}
