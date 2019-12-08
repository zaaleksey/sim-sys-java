package simsys.mm1;

import java.util.ArrayDeque;

public class Queue {

    private ArrayDeque<Demand> queue;

    public Queue() {
        queue = new ArrayDeque<>();
    }

    public int size() {
        return queue.size();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public boolean push(Demand demand) {
        return queue.add(demand);
    }

    public Demand pop() {
        return queue.remove();
    }

    public Demand getFirst() {
        return queue.getFirst();
    }

    public Demand getLast() {
        return queue.getLast();
    }
}
