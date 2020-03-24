package simsys.system_components.api.container;

import simsys.system_components.api.node.Node;

import java.util.List;
import java.util.Map;

public abstract class ComponentContainer extends Node {
    List<Node> components;
    Map<Node, Node> communicationBetweenComponents;

    public void addCommunication(Node out, Node in) {
        communicationBetweenComponents.put(out, in);
    }
}
