package simsys.core.exception;

/**
 * This exception is thrown if no item was found in the collection.
 */
public class NoItemInCollection extends Exception {

  public NoItemInCollection(String id) {
    super("A component with id" + id + "in the collection does not exist!");
  }

}
