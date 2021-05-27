package simsys.component.system;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import org.apache.commons.lang3.ArrayUtils;
import simsys.core.agent.AgentAction;
import simsys.core.context.SimulationContext;
import simsys.core.parcel.Parcel;
import simsys.entity.demand.Demand;
import simsys.entity.queue.Queue;
import simsys.random.ExponentialRandomVariable;
import simsys.random.RandomVariable;

public class QueueingSystemWithTwoServer extends AbstractQueueingSystem {

  private static final double FAST_SERVER_RATE = 1;
  private static final double SLOW_SERVER_RATE = 0.9;

  private static final String SOJOURN_TIME = "SOJOURN_TIME";


  private Demand fastServerDemand;
  private Demand slowServerDemand;


  private RandomVariable slowServerRV;
  private RandomVariable fastServerRV;


  private double[][] qTable;
  private final int stateNumber;


  private final int ACTION_NUMBER = 2;

  private double alpha;
  private double gamma;
  private double epsilon;


  private final Random r;


  private double accumulatedRewardBetweenMakingDecisions;
  private int lastAction;
  private int stateBeforeLastAction;
  private double previousTimeForPerformActionCall;
  private double totalReward;
  public ArrayList<Double> rewardRates;
  private double warmUpDuration;
  private double lastTimeDecision = 0;

  public QueueingSystemWithTwoServer(SimulationContext context, Queue queue,
      RandomVariable randomVariable, String agentName,
      double learningRate, double rewardDecay, double eGreedy, double warmUpDuration) {
    super(context, queue, randomVariable, agentName);

    r = new Random();

    this.alpha = learningRate;
    this.gamma = rewardDecay;
    this.epsilon = eGreedy;
    this.warmUpDuration = warmUpDuration;

    this.stateNumber = queue.capacity() + 1;

    this.slowServerRV = new ExponentialRandomVariable(r, SLOW_SERVER_RATE);
    this.fastServerRV = new ExponentialRandomVariable(r, FAST_SERVER_RATE);

    accumulatedRewardBetweenMakingDecisions = 0;
    lastAction = 1;
    stateBeforeLastAction = 1;
    previousTimeForPerformActionCall = 0;
    totalReward = 0;
    rewardRates = new ArrayList<>();
    initQTable();
  }


  public double getRewardForCurrentState() {
    int q = getNumberDemandInSystem();
    return -q;
  }

  public int getCurrentState() {
    return queue.size();
  }

  public void trackRewardRate(double rewardRate) {
    rewardRates.add(rewardRate);
  }

  public void updateReward() {
    double delta = context.getCurrentTime() - previousTimeForPerformActionCall;
    accumulatedRewardBetweenMakingDecisions += getRewardForCurrentState() * delta;
    previousTimeForPerformActionCall = context.getCurrentTime();
  }


  // печально, но параметры пока в performAction передать нельзя,  поэтому нужно делать по функции на прибор
  public void leaveFastServer() {
    updateReward();
    if (fastServerDemand != null) {
      fastServerDemand.setLeavingTime(context.getCurrentTime());
      logValue(SOJOURN_TIME, fastServerDemand.getLeavingTime() - fastServerDemand.getArrivalTime());
      fastServerDemand = null;
      performAction(this::startFastService);
    }
  }

  public void leaveSlowServer() {
    updateReward();
    if (slowServerDemand != null) {
      slowServerDemand.setLeavingTime(context.getCurrentTime());
      logValue(SOJOURN_TIME, slowServerDemand.getLeavingTime() - slowServerDemand.getArrivalTime());
      slowServerDemand = null;
      performAction(this::startSlowServer);
    }
  }


  public void startFastService() {
    updateReward();
    if (queue.size() > 0 && fastServerDemand == null) {
      fastServerDemand = queue.remove();
      fastServerDemand.setServiceStartTime(context.getCurrentTime());
      performActionAfterTimeout(this::leaveFastServer, fastServerRV.nextValue());
    }
  }


  public void startSlowServer() {
    updateReward();
    if (queue.size() > 0 && slowServerDemand == null) {
      double reward =
          accumulatedRewardBetweenMakingDecisions;
      lastTimeDecision = context.getCurrentTime();

      int oldState = stateBeforeLastAction;
      int currentState = getCurrentState();
      updateQTable(oldState, lastAction, reward, currentState);

      totalReward += accumulatedRewardBetweenMakingDecisions;
      accumulatedRewardBetweenMakingDecisions = 0;

      // Table was updated, make new decision and save it
      int action = makeDecision(currentState);
      lastAction = action;
      stateBeforeLastAction = currentState;

      trackRewardRate(totalReward / context.getCurrentTime());

      if (action == 0) {
        slowServerDemand = queue.remove();
        slowServerDemand.setServiceStartTime(context.getCurrentTime());
        performActionAfterTimeout(this::leaveSlowServer, slowServerRV.nextValue());
      }
    }
  }


  @Override
  public void startService() {
    if (queue.size() > 0) {
      performAction(this::startFastService);
      performAction(this::startSlowServer);
    }
  }

  @Override
  public void endService() {
    //  оно не вызывается
    System.out.println("endService");
  }

  @Override
  public void receive(Parcel parcel) {
    queue.add((Demand) parcel);
    performAction(this::startService);
  }


  private int getNumberDemandInSystem() {
    int processingDemandNumber = 0;
    if (fastServerDemand != null) {
      processingDemandNumber++;
    }
    if (slowServerDemand != null) {
      processingDemandNumber++;
    }
    return queue.size() + processingDemandNumber;
  }

  private void initQTable() {
    qTable = new double[stateNumber][ACTION_NUMBER];
  }

  private void updateQTable(int state, int action, double reward, int newState) {
    double qCurrentValue = qTable[state][action];
    double qTargetValue = reward + gamma * max(qTable[newState]);
    qTable[state][action] += alpha * (qTargetValue - qCurrentValue);
  }

  private int makeDecision(int state) {
    if (r.nextDouble() < epsilon || context.getCurrentTime() < warmUpDuration) {
      return r.nextInt(ACTION_NUMBER);
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
    double maximum = Arrays.stream(d).max().getAsDouble();
    int maxArg = ArrayUtils.indexOf(d, maximum);
    maxElementAndIndex[0] = maximum;
    maxElementAndIndex[1] = maxArg;
    return maxElementAndIndex;
  }

  public void printQTable() {
    for (int i = 0; i < stateNumber; i++) {
      System.out.print(i + ":\t");
      for (int j = 0; j < ACTION_NUMBER; j++) {
        System.out.print(qTable[i][j] + " ");
      }
      System.out.println();
    }
  }


  public ArrayList<Integer> getPolicy() {
    var result = new ArrayList<Integer>();
    for (int i = 1; i < stateNumber; i++) {
      result.add(argMax(qTable[i]));
    }
    return result;
  }

}
