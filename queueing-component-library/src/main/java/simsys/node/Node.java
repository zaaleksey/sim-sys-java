package simsys.node;

import java.util.Collection;
import simsys.core.component.Component;
import simsys.entity.demand.Demand;

public interface Node extends Component {

  Collection<Node> getNodes();

  void addNode(Node node);

  void receive(Demand d);
}
