package simsys.node;

import java.util.ArrayList;
import java.util.Collection;

public abstract class AbstractNode implements Node {

  protected ArrayList<Node> nodes;

  @Override
  public Collection<Node> getNodes() {
    return nodes;
  }

  @Override
  public void addNode(Node node) {
    nodes.add(node);
  }

}
