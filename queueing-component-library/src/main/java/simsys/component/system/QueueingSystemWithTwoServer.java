package simsys.component.system;

import simsys.component.system.server.Server;
import simsys.core.context.SimulationContext;
import simsys.core.parcel.Parcel;
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

    private double[][] qTable;
    private final int stateNumber;
    private final int actionNumber;

    private double alpha;
    private double gamma;
    private double epsilon;

    private boolean act = false;

    private final Random r;

    public QueueingSystemWithTwoServer(SimulationContext context, Queue queue,
                                       RandomVariable randomVariable, String agentName,
                                       double learningRate, double rewardDecay, double eGreedy) {
        super(context, queue, randomVariable, agentName);

        servers = new HashMap<>();

        r = new Random(0);
        servers.put(FAST_SERVER,
                new Server(context, queue, new ExponentialRandomVariable(r, FAST_SERVER_RATE), FAST_SERVER));
        servers.put(SLOW_SERVER,
                new Server(context, queue, new ExponentialRandomVariable(r, SLOW_SERVER_RATE), SLOW_SERVER));

        this.alpha = learningRate;
        this.gamma = rewardDecay;
        this.epsilon = eGreedy;

        this.stateNumber = queue.capacity();
        this.actionNumber = servers.values().size();

        initQTable();
    }

    @Override
    public void startService() {
        if (queue.size() > 0) {
            if (!servers.get(FAST_SERVER).isBusy()) {
                servers.get(FAST_SERVER).startService();
            }
            if (act && !servers.get(SLOW_SERVER).isBusy() && queue.size() > 0) {
                servers.get(SLOW_SERVER).startService();
                act = false;
            }
        }
    }

    @Override
    public void endService() {
        // управление и работа с q-таблицей
        // переменную act переключить на true если необходимо отправлять требование на медленный прибор
        
        if (servers.get(FAST_SERVER).isBusy()) {
            servers.get(FAST_SERVER).endService();
        }
        if (servers.get(SLOW_SERVER).isBusy()) {
            servers.get(SLOW_SERVER).endService();
        }
    }

    @Override
    public void receive(Parcel parcel) {
        // управление и работа с q-таблицей
        // переменную act переключить на true если необходимо отправлять требование на медленный прибор

        queue.add((Demand) parcel);
        performAction(this::startService);
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

    private void initQTable() {
        qTable = new double[stateNumber][actionNumber];
//        printQTable();
    }

    private void updateQTable(int state, int action, double reward, int newState) {
        double qCurrentValue = qTable[state][action];
        double qTargetValue = reward + gamma * max(qTable[newState]);
        qTable[state][action] += alpha * (qCurrentValue - qTargetValue);
    }

    private int makeDecision(int state) {
        if (r.nextDouble() < epsilon) {
            return r.nextInt(actionNumber);
        } else {
            return argMax(qTable[state]);
        }
    }

    private double max(double[] d) {
        return findMaximumElementAndItsIndex(d)[0];
    }

    private int argMax(double[] d) {
        return (int) findMaximumElementAndItsIndex(d)[1];
    }

    private double[] findMaximumElementAndItsIndex(double[] d) {
        double[] maxElementAndIndex = new double[2];
        double max = Integer.MIN_VALUE;
        int maxIndex = 0;

        for (int i = 0; i < d.length; i++) {
            if (d[i] > max) {
                max = d[i];
                maxIndex = i;
            }
        }
        maxElementAndIndex[0] = max;
        maxElementAndIndex[1] = maxIndex;
        return maxElementAndIndex;
    }

    public void printQTable() {
        for (int i = 0; i < stateNumber; i++) {
            System.out.print(i + ":\t");
            for (int j = 0; j < actionNumber; j++) {
                System.out.print(qTable[i][j] + " ");
            }
            System.out.println();
        }
    }

}
