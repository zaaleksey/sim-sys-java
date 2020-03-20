package simsys.system_components.api;

public interface Device extends Node {
    void toServe(Demand demand);
}
