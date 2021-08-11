package simsys.core.exception;

/**
 *
 */
public class AgentsCollisionException extends RuntimeException {

  /**
   * @param name agent name
   */
  public AgentsCollisionException(String name) {
    super("An agent with the same name '" + name + "' already exists in the system!");
  }
}
