package simsys.node;

import simsys.core.component.Component;
import simsys.entity.demand.Demand;

import java.util.Collection;

public interface Node extends Component {
    Collection<Node> getNodes();

    void addNode(Node node);

    void receive(Demand d);
}
