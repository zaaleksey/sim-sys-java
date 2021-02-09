package simsys.component;

import simsys.node.Node;

@FunctionalInterface
public interface RouteFunction {

  public Node route();

}
