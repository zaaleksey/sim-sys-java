package simsys.examples.agent;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;
import simsys.component.source.SourceAgent;
import simsys.component.system.QueueingSystemWithTwoServer;
import simsys.core.condition.TimeStopCondition;
import simsys.core.context.SimulationContext;
import simsys.core.context.SimulationContextImpl;
import simsys.core.model.AgentBasedSimulationModel;
import simsys.entity.queue.Queue;
import simsys.entity.queue.QueueFIFO;
import simsys.random.ExponentialRandomVariable;

public class SlowServerProblem {

  public static void main(String[] args) {
    SimulationContext context = SimulationContextImpl.getContext();
    Random r = new Random();
    double simulationDuration = 10_000_000;
    double warmUpDuration = simulationDuration * 0;

    double lambda = 3;
    SourceAgent source = new SourceAgent(context,
        new ExponentialRandomVariable(r, lambda),
        "Source");

    double learningRate = 0.1;
    double rewardDecay = 1;
    double eGreedy = 0.4;

    int capacity = 10;
    Queue queue = new QueueFIFO(capacity);
    QueueingSystemWithTwoServer queueing =
        new QueueingSystemWithTwoServer(context, queue, new ExponentialRandomVariable(r, 0),
            "QueueingSystemWithTwoServer", learningRate, rewardDecay, eGreedy, warmUpDuration);

    source.setReceiver(queueing);
    var model = new AgentBasedSimulationModel(context);
    model.setStopCondition(new TimeStopCondition(simulationDuration));
    model.addAgents(Arrays.asList(source, queueing));
    source.sendDemand();

    long startTime = System.nanoTime();
    model.run();
    long endTime = System.nanoTime();
    long timeElapsed = endTime - startTime;
    System.out.println("Elapsed time = " + (double) (timeElapsed) / 1_000_000_000);
    System.out.println("Q-table:");
    queueing.printQTable();

    System.out.println("Sojourn TIme: " + queueing.getStatisticForVariable("SOJOURN_TIME").mean());

    try {
      FileWriter fileWriter = new FileWriter("q-learn.dat");
      PrintWriter printWriter = new PrintWriter(fileWriter);
      // Plot with some step,to reduce amount of points
      int step = queueing.rewardRates.size() / 100_000;
      for (int i = 0; i < queueing.rewardRates.size(); i += step) {
        printWriter.println(queueing.rewardRates.get(i));
      }
      fileWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    System.out.println("Policy");
    var policy = queueing.getPolicy();
    System.out.println(policy);

    System.out.println("Completed");

//    System.out.println("Average service time on a FAST server is "
//        + queueing.getServerStatistic("SOJOURN_TIME").get(0).mean());
//
//    System.out.println("Average service time on a SLOW server is "
//        + queueing.getServerStatistic("SOJOURN_TIME").get(1).mean());
  }

}
