package simsys.system_components.api.node;

public abstract class Node {
    private static long COUNTER = 0;
    private long id;

    public Node() {
        COUNTER++;
        id = COUNTER;
    }

    public long getId() {
        return id;
    }

    public void activate(NodeType type) {
        if(type == NodeType.RECEIVER) {
            receive();
        } else if(type == NodeType.SENDER) {
            send();
        }
    }

    public abstract void receive();
    public abstract void send();
}
