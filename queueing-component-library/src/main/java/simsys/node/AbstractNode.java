package simsys.node;

import java.util.ArrayList;
import java.util.Collection;
import simsys.core.component.AbstractComponent;

public abstract class AbstractNode extends AbstractComponent implements Node {

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
