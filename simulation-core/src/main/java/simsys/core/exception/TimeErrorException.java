package simsys.core.exception;

/**
 *
 */
public class TimeErrorException extends Exception {

  /**
   * @param time time to set
   */
  public TimeErrorException(double time) {
    super("Impossible time to set the clock. Time: " + time);
  }

}
