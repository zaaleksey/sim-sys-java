package simsys.mm1;

import java.util.ArrayDeque;

public class QueueMM1 {
    private static ArrayDeque<DemandMM1> queue = new ArrayDeque<>();

    public static int size() {
        return queue.size();
    }

    public static boolean isEmpty() {
        return queue.isEmpty();
    }

    public static boolean push(DemandMM1 demandMM1) {
        return queue.add(demandMM1);
    }

    public static DemandMM1 pop() {
        return queue.remove();
    }

    public static DemandMM1 getFirst() {
        return queue.getFirst();
    }
}
