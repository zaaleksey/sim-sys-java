package simsys.examples.events;

import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import simsys.core.CoreConfig;
import simsys.core.condition.TimeStopCondition;
import simsys.core.context.SimulationContext;
import simsys.core.event.Event;
import simsys.core.event.HandledEvent;
import simsys.core.event.handler.TimeoutHandler;
import simsys.core.exception.ImpossibleEventTime;
import simsys.core.model.SimulationModelImpl;
import simsys.entity.demand.Demand;
import simsys.entity.demand.SimpleDemand;
import simsys.entity.queue.Queue;
import simsys.entity.queue.QueueFIFO;
import simsys.random.ExponentialRandomVariable;
import simsys.random.RandomVariable;

@Slf4j
public class SimpleQueueingSystem {

  //don't use global variable!!!
  //only for very fast examples
  static final RandomVariable serviceTimes = new ExponentialRandomVariable(new Random(), 4);
  static Demand processingDemand;
  static double averageServiceTime = 0;
  static double countOfDemands = 0;


  public static Event createDemandEvent(double lambda, Queue queue, SimulationContext context) {
    //we create some event and make this periodic
    HandledEvent createDemand = new HandledEvent();
    TimeoutHandler timeout = new TimeoutHandler(
        new ExponentialRandomVariable(new Random(), lambda));
    timeout.setSimulationContext(context);
    createDemand.addHandler(timeout);
    //just a very simple logger
    createDemand.addHandler(event -> {
          LOGGER.info("Create demand event");
          LOGGER.info("Queue size = " + queue.size());
        }
    );

    // also we add some logic - creating of new demands and move to the queue
    //here we need think about dependency between events, so arrive -> start service
    //it's very nice create an event activated by a condition
    //but here we'll use a simple way
    createDemand.addHandler(event -> {
      Demand demand = new SimpleDemand(context.getCurrentTime());
      queue.add(demand);
      //the simple and stupid way - to create an service event in place
      context.getEventProvider().add(createStartServiceEvent(queue, context));
    });

    return createDemand;
  }

  //this way have one more disadvantages - we create a new Event each time
  //we need reuse events
  public static Event createStartServiceEvent(Queue queue, SimulationContext context) {
    HandledEvent serviceEvent = new HandledEvent();
    serviceEvent.setActivateTime(context.getCurrentTime());

    serviceEvent.addHandler(event -> {
      LOGGER.info("Start service event");
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
    });

    return serviceEvent;
  }

  public static Event createEndServiceEvent(double activationTime, Queue queue,
      SimulationContext context) {

    HandledEvent endServiceEvent = new HandledEvent();
    endServiceEvent.setActivateTime(activationTime);

    endServiceEvent.addHandler(event -> {
      LOGGER.info("End service event");
      if (processingDemand != null) {
        processingDemand.setLeavingTime(context.getCurrentTime());
        countOfDemands++;
        averageServiceTime += processingDemand.getLeavingTime() - processingDemand.getArrivalTime();

        LOGGER.info("We've processed one more demand");
        processingDemand = null;

        //end of service -> try to start service of a new demand
        context.getEventProvider().add(createStartServiceEvent(queue, context));

      }
    });

    return endServiceEvent;
  }

  public static void simpleQueueingSystems() {
//    SimulationContext simulationContext = SimulationContextImpl.getEmptyInstance();
    SimulationContext simulationContext =
        new AnnotationConfigApplicationContext(CoreConfig.class).getBean(SimulationContext.class);

    SimulationModelImpl model = new SimulationModelImpl(simulationContext);

    Queue queue = new QueueFIFO();
    Event event = createDemandEvent(2, queue, simulationContext);

    try {
      simulationContext.getEventProvider().add(event);
    } catch (ImpossibleEventTime impossibleEventTime) {
      impossibleEventTime.printStackTrace();
    }
    model.setStopCondition(new TimeStopCondition(1000));
    model.run();
  }

  public static void main(String[] args) {
    simpleQueueingSystems();

//    correct answer  = 1/(mu - lambda) = 0.5
    System.out.println("Average service time is " + averageServiceTime / countOfDemands);

  }

}
