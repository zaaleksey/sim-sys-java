package simsys.system_components.api.components;

import simsys.system_components.api.demand.Demand;
import simsys.system_components.api.node.Node;

public abstract class Device extends Node {
    public abstract void toServe(Demand demand);
}
