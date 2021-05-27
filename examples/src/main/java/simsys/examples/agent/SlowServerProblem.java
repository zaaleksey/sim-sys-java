package simsys.examples.agent;

import simsys.component.source.SourceAgent;
import simsys.component.system.QueueingSystemWithTwoServer;
import simsys.core.condition.TimeStopCondition;
import simsys.core.context.SimulationContext;
import simsys.core.context.SimulationContextImpl;
import simsys.core.model.AgentSimulationModelML;
import simsys.entity.queue.Queue;
import simsys.entity.queue.QueueFIFO;
import simsys.random.ExponentialRandomVariable;
import symsys.statistic.StatisticAccumulator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SlowServerProblem {

    public static void main(String[] args) {
        SimulationContext context = SimulationContextImpl.getContext();
        Random r = new Random(0);
        double lambda = 1;
        SourceAgent source = new SourceAgent(context,
                new ExponentialRandomVariable(r, lambda),
                "Source");

        double learningRate = 1.0;
        double rewardDecay = 0.9;
        double eGreedy = 0.4;

        int capacity = 20;
        Queue queue = new QueueFIFO(capacity);
        QueueingSystemWithTwoServer queueing =
                new QueueingSystemWithTwoServer(context, queue, new ExponentialRandomVariable(r, 0),
                        "QueueingSystemWithTwoServer", learningRate, rewardDecay, eGreedy);

        source.setReceiver(queueing);

        AgentSimulationModelML slowServer = new AgentSimulationModelML(context);
        slowServer.setStopCondition(new TimeStopCondition(1_000_000));
        slowServer.addAgents(Arrays.asList(source, queueing));
        source.sendDemand();

        List<List<StatisticAccumulator>> statistic = new ArrayList<>();
        int maxSteps = 1000;
        int step = 0;

        long startTime = System.nanoTime();
        while (step <= maxSteps) {
            slowServer.run();
            statistic.add(queueing.getServerStatistic("SOJOURN_TIME"));
            slowServer.reset();
            step++;
        }
        long endTime = System.nanoTime();
        long timeElapsed = endTime - startTime;
        System.out.println("Elapsed time = " + (double) (timeElapsed) / 1_000_000_000);

        System.out.println("Q-table:");
        queueing.printQTable();

        System.out.println("Average service time on a FAST server is "
                + queueing.getServerStatistic("SOJOURN_TIME").get(0).mean());

        System.out.println("Average service time on a SLOW server is "
                + queueing.getServerStatistic("SOJOURN_TIME").get(1).mean());
    }

}
