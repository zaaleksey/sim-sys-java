package simsys.core.exception;

public class AgentsCollision extends RuntimeException {

  public AgentsCollision(String name) {
    super("An agent with the same name" + name + "already exists in the system!");
  }
}
