package simsys.core.exception;

public class NoItemInCollection extends Exception {

  public NoItemInCollection(String id) {
    super("A component with id" + id + "in the collection does not exist!");
  }

}
