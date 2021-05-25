package simsys.component.system;

import simsys.component.system.server.Server;
import simsys.core.context.SimulationContext;
import simsys.entity.demand.Demand;
import simsys.entity.queue.Queue;
import simsys.random.ExponentialRandomVariable;
import simsys.random.RandomVariable;
import symsys.statistic.StatisticAccumulator;

import java.util.*;

public class QueueingSystemWithTwoServer extends AbstractQueueingSystem {

    private static final String FAST_SERVER = "FAST_SERVER";
    private static final double FAST_SERVER_RATE = 0.8;
    private static final String SLOW_SERVER = "SLOW_SERVER";
    private static final double SLOW_SERVER_RATE = 0.2;

    private final Map<String, Server> servers;
    private final Map<String, Demand> processingDemands;

    public QueueingSystemWithTwoServer(SimulationContext context, Queue queue,
                                       RandomVariable randomVariable, String agentName) {
        super(context, queue, randomVariable, agentName);

        Random r = new Random(0);
        servers = new HashMap<>();
        processingDemands = new HashMap<>();

        servers.put(FAST_SERVER,
                new Server(context, queue, new ExponentialRandomVariable(r, FAST_SERVER_RATE), FAST_SERVER));
        servers.put(SLOW_SERVER,
                new Server(context, queue, new ExponentialRandomVariable(r, SLOW_SERVER_RATE), SLOW_SERVER));
        processingDemands.put(FAST_SERVER, null);
        processingDemands.put(SLOW_SERVER, null);
    }

    @Override
    public void startService() {
        if (queue.size() > 0) {
            if (!servers.get(FAST_SERVER).isBusy()) {
                servers.get(FAST_SERVER).startService();
            } else if (!servers.get(SLOW_SERVER).isBusy()) {
                servers.get(SLOW_SERVER).startService();
            }
        }
    }

    @Override
    public void endService() {
        if (servers.get(FAST_SERVER).isBusy()) {
            servers.get(FAST_SERVER).endService();
        }
        if (servers.get(SLOW_SERVER).isBusy()) {
            servers.get(SLOW_SERVER).endService();
        }
    }

    public List<StatisticAccumulator> getServerStatistic(String name) {
        ArrayList<StatisticAccumulator> accumulators = new ArrayList<>();
        for (Server server : servers.values()) {
            accumulators.add(server.getStatisticForVariable(name));
        }
        return accumulators;
    }

    private int getNumberDemandInSystem() {
        int processingDemandNumber = 0;
        if (servers.get(FAST_SERVER).isBusy()) processingDemandNumber++;
        if (servers.get(SLOW_SERVER).isBusy()) processingDemandNumber++;
        return queue.size() + processingDemandNumber;
    }

}
