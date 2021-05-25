package simsys.core.common;

/**
 * Factory interface for creating objects of type T.
 *
 * @param <T> type of object to create
 */
public interface Factory<T> {

  /**
   * A method for creating a complex object such as a HandledEventBuilder.
   *
   * @return object of type T
   */
  T create();

}
