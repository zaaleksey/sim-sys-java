package simsys.system_components.api;

public interface Source extends Node {
    void sendRequest(Node recipient);
}
