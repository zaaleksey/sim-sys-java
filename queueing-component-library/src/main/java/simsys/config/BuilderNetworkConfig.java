package simsys.config;

import simsys.node.Node;

public interface BuilderNetworkConfig {

    void addComponents(Node node);
    void linkComponents(Node out, Node in);

}
