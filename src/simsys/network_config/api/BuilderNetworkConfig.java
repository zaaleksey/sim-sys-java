package simsys.network_config.api;

import simsys.system_components.api.node.Node;

public interface BuilderNetworkConfig {
    void addComponents(Node node);
    void linkComponents(Node out, Node in);
}
