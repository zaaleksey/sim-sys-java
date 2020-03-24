package simsys.system_components.api;

public interface Node {
    void receive(Demand demand);
    void send(Demand demand);
    void activate();
}
