package simsys.system_components.api.components;

import simsys.system_components.api.node.Node;

public abstract class Source extends Node{
    abstract void sendRequest(Node recipient);
}
