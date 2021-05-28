package simsys.core.context;

/**
 * A functional interface for observing some variables.
 */
@FunctionalInterface
public interface VariableObserver {

  /**
   * Returns some value of a variable.
   *
   * @return value of a variable
   */
  double get();

}
