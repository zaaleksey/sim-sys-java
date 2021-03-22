package simsys.node;

import java.util.Collection;
import simsys.entity.demand.Demand;

public interface Node {

  Collection<Node> getNodes();

  void addNode(Node node);

  void receive(Demand d);

}
