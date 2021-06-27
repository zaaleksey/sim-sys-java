package simsys.examples.events;

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

import java.util.Random;


/**
 * This is an example of a Queuing System type MM1. Arrivals occur at rate λ according to a Poisson process.
 * Service times are exponentially distributed with rate parameter μ so that 1/μ is the mean service time.
 * A single server serves customers one at a time from the front of the queue, according to a first-come,
 * first-served discipline. When the service is complete the customer leaves the queue and the number
 * of customers in the system reduces by one.

 * Implementation by defining simple events.
 */
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
          Demand demand = new SimpleDemand(context.getCurrentTime());
          queue.add(demand);
          context.getEventProvider().add(createStartServiceEvent(queue, context));
        }).build();

    return createDemand;
  }

  public static Event createStartServiceEvent(Queue queue, SimulationContext context) {

    HandledEvent serviceEvent = new HandledEventBuilder(context)
        .addHandler(event -> {
          if (!queue.isEmpty() && processingDemand == null) {
            Demand demand = queue.remove();
            demand.setServiceStartTime(context.getCurrentTime());
            processingDemand = demand;
            double delay = serviceTimes.nextValue();
            double endServiceTime = context.getCurrentTime() + delay;
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
          if (processingDemand != null) {
            processingDemand.setLeavingTime(context.getCurrentTime());
            countOfDemands++;
            averageServiceTime +=
                processingDemand.getLeavingTime() - processingDemand.getArrivalTime();
            processingDemand = null;
            context.getEventProvider().add(createStartServiceEvent(queue, context));
          }
        }).build();

    endServiceEvent.setActivateTime(activationTime);
    return endServiceEvent;
  }

  public static void simpleQueueingSystems() {
    SimulationContext context = SimulationContextImpl.getContext();
    SimulationModelImpl model = new SimulationModelImpl(context);
    double lambda = 2;

    Queue queue = new QueueFIFO();
    Event event = createDemandEvent(lambda, queue, context);
    context.getEventProvider().add(event);
    model.setStopCondition(new TimeStopCondition(10_000_000));
    model.run();
  }

  public static void main(String[] args) {
    long startTime = System.nanoTime();
    simpleQueueingSystems();
    long endTime = System.nanoTime();

    // correct answer  = 1/(mu - lambda) = 0.5
    System.out.println("Average service time is " + averageServiceTime / countOfDemands);

    double timeElapsed = endTime - startTime;
    System.out.println("Elapsed time = " + timeElapsed / 1_000_000_000);
  }

}
