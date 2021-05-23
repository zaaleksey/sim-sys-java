package simsys.examples.events;


import com.google.common.base.Strings;
import simsys.core.clock.Clock;
import simsys.core.clock.ClockImpl;
import simsys.core.condition.TimeStopCondition;
import simsys.core.context.SimulationContext;
import simsys.core.context.SimulationContextImpl;
import simsys.core.environment.Environment;
import simsys.core.environment.EnvironmentImpl;
import simsys.core.event.HandledEvent;
import simsys.core.event.HandledEventBuilderFactory;
import simsys.core.event.handler.TimeoutHandler;
import simsys.core.exception.ImpossibleEventTime;
import simsys.core.model.SimulationModelImpl;
import simsys.core.provider.EventProvider;
import simsys.core.provider.EventProviderImpl;
import simsys.random.ExponentialRandomVariable;

import java.util.Collections;
import java.util.Random;

/**
 *
 */
public class TimerEventSimulation {

  public static void simpleExample() {
    SimulationContext context = SimulationContextImpl.getContext();

    HandledEvent periodic = new HandledEvent();
    TimeoutHandler timeout = new TimeoutHandler(
        new ExponentialRandomVariable(new Random(), 1));
    timeout.setSimulationContext(context);

    periodic.addHandler(timeout);
    context.getEventProvider().add(periodic);

    SimulationModelImpl model = new SimulationModelImpl(context);
    model.setStopCondition(new TimeStopCondition(1_000));
    model.run();
  }

  public static void factoryExample() {
    SimulationContext context = SimulationContextImpl.getContext();

    HandledEventBuilderFactory eventBuilderFactory = new HandledEventBuilderFactory(
        context,
        event -> System.out.println("Before handler for event " + event),
        event -> System.out.println("After handler for event " + event));

    HandledEvent randomPeriodic = eventBuilderFactory
        .create()
        .periodic(new ExponentialRandomVariable(new Random(), 1))
        .addHandler(event -> System.out
            .println("Message from periodic random event: " + event.getActivateTime()))
        .build();

    HandledEvent constPeriodic = eventBuilderFactory
        .create()
        .periodic(3)
        .addHandler(event -> System.out
            .println("Message from periodic const event: " + event.getActivateTime()))
        .build();

    try {
      context.getEventProvider().add(randomPeriodic);
      context.getEventProvider().add(constPeriodic);
    } catch (ImpossibleEventTime impossibleEventTime) {
      impossibleEventTime.printStackTrace();
    }

    SimulationModelImpl model = new SimulationModelImpl(context);
    model.setStopCondition(new TimeStopCondition(1_000));
    model.run();
  }

  public static void main(String[] args) {
    simpleExample();

    System.out.println(Strings.repeat("#", 50));

//    factoryExample();
  }

}
