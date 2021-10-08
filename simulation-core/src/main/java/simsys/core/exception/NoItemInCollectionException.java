package simsys.core.exception;

/**
 * This exception is thrown if no item was found in the collection.
 */
public class NoItemInCollectionException extends Exception {

  /**
   * @param id item id
   */
  public NoItemInCollectionException(String id) {
    super("A component with id" + id + "in the collection does not exist!");
  }

}
