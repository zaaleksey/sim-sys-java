package simsys.environment.api;

import simsys.event_manager.api.EventManager;
import simsys.network_config.api.NetworkConfig;

public interface Environment {
    EventManager getEventManager();
    void setEventManager(EventManager eventManager);
    NetworkConfig getNetworkConfig();
    void setNetworkConfig(NetworkConfig networkConfig);
}
