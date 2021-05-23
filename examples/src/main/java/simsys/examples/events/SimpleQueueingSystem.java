package simsys.examples.events;

import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import simsys.core.condition.TimeStopCondition;
import simsys.core.context.SimulationContext;
import simsys.core.context.SimulationContextImpl;
import simsys.core.event.Event;
import simsys.core.event.HandledEvent;
import simsys.core.event.HandledEvent.HandledEventBuilder;
import simsys.core.model.SimulationModelImpl;
import simsys.entity.demand.Demand;
import simsys.entity.demand.SimpleDemand;
import simsys.entity.queue.Queue;
import simsys.entity.queue.QueueFIFO;
import simsys.random.ExponentialRandomVariable;
import simsys.random.RandomVariable;

@Slf4j
public class SimpleQueueingSystem {

  static final RandomVariable serviceTimes = new ExponentialRandomVariable(new Random(), 4);
  static Demand processingDemand;
  static double averageServiceTime = 0;
  static double countOfDemands = 0;


  public static Event createDemandEvent(double lambda, Queue queue, SimulationContext context) {
    HandledEvent createDemand = new HandledEventBuilder(context)
        .periodic(new ExponentialRandomVariable(new Random(), lambda))
        .addHandler(event -> {
          LOGGER.debug("Create demand event");
          LOGGER.debug("Queue size = {}", queue.size());
        })
        .addHandler(event -> {
          Demand demand = new SimpleDemand(context.getCurrentTime());
          queue.add(demand);
          context.getEventProvider().add(createStartServiceEvent(queue, context));
        }).build();
    return createDemand;
  }

  public static Event createStartServiceEvent(Queue queue, SimulationContext context) {
    HandledEvent serviceEvent = new HandledEventBuilder(context)
        .addHandler(event -> {
          LOGGER.debug("Start service event");
          if (!queue.isEmpty() && processingDemand == null) {
            Demand demand = queue.remove();
            demand.setServiceStartTime(context.getCurrentTime());
            //move the demand to sever
            processingDemand = demand;

            double delay = serviceTimes.nextValue();
            double endServiceTime = context.getCurrentTime() + delay;
            //add endServiceEvent
            context.getEventProvider().add(createEndServiceEvent(endServiceTime, queue, context));
          }
        }).build();

    serviceEvent.setActivateTime(context.getCurrentTime());
    return serviceEvent;
  }

  public static Event createEndServiceEvent(double activationTime, Queue queue,
      SimulationContext context) {

    HandledEvent endServiceEvent = new HandledEventBuilder(context)
        .addHandler(event -> {
          LOGGER.debug("End service event");
          if (processingDemand != null) {
            processingDemand.setLeavingTime(context.getCurrentTime());
            countOfDemands++;
            averageServiceTime +=
                processingDemand.getLeavingTime() - processingDemand.getArrivalTime();

            LOGGER.debug("We've processed one more demand");
            processingDemand = null;

            //end of service -> try to start service of a new demand
            context.getEventProvider().add(createStartServiceEvent(queue, context));
          }
        }).build();

    endServiceEvent.setActivateTime(activationTime);
    return endServiceEvent;
  }

  public static void simpleQueueingSystems() {
    SimulationContext simulationContext = SimulationContextImpl.getContext();
    SimulationModelImpl model = new SimulationModelImpl(simulationContext);
    double lambda = 2;

    Queue queue = new QueueFIFO();
    Event event = createDemandEvent(lambda, queue, simulationContext);
    simulationContext.getEventProvider().add(event);
    model.setStopCondition(new TimeStopCondition(1_000_000));
    model.run();
  }

  public static void main(String[] args) {
    long startTime = System.nanoTime();

    simpleQueueingSystems();

    //correct answer  = 1/(mu - lambda) = 0.5
    System.out.println("Average service time is " + averageServiceTime / countOfDemands);

    long endTime = System.nanoTime();
    double timeElapsed = endTime - startTime;
    System.out.println("Elapsed time = " + timeElapsed / 1_000_000_000);

  }

}
