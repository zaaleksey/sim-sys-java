package simsys.system_components.api;

public interface Queue {
    int size();
    boolean pushDemand(Demand demand);
    Demand popDemand();
    Demand peekDemand();

    default boolean isEmpty() {
        return size() == 0;
    }
}
