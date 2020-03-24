package simsys.system_components.api.components;

import simsys.system_components.api.demand.Demand;
import simsys.system_components.api.node.Node;

public abstract class Queue extends Node {
    public abstract int size();
    public abstract boolean pushDemand(Demand demand);
    public abstract Demand popDemand();
    public abstract Demand peekDemand();

    public boolean isEmpty() {
        return size() == 0;
    }
}
