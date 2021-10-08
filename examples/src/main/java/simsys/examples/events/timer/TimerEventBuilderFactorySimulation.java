package simsys.examples.events.timer;


import simsys.core.context.SimulationContext;
import simsys.core.context.SimulationContextImpl;
import simsys.core.event.HandledEvent;
import simsys.core.event.HandledEventBuilderFactory;
import simsys.core.model.SimulationModelImpl;
import simsys.random.ExponentialRandomVariable;

/**
 * Implementation of a timer with two events. The first event (constPeriodic) is activated at
 * regular intervals. The second event (randomPeriodic) is activated at intervals of exponential
 * distribution with some rate.
 */
public class TimerEventBuilderFactorySimulation {

  public static void main(String[] args) {
    SimulationContext context = SimulationContextImpl.getContext();

    HandledEventBuilderFactory eventBuilderFactory = new HandledEventBuilderFactory(
        context,
        event -> System.out.println("Before handler for event " + event),
        event -> System.out.println("After handler for event " + event));

    HandledEvent randomPeriodic = eventBuilderFactory
        .create()
        .periodic(new ExponentialRandomVariable(1))
        .addHandler(event -> System.out
            .println("Message from periodic random event: " + event.getActivateTime()))
        .build();

    HandledEvent constPeriodic = eventBuilderFactory
        .create()
        .periodic(3)
        .addHandler(event -> System.out
            .println("Message from periodic const event: " + event.getActivateTime()))
        .build();

    context.getEventProvider().add(randomPeriodic);
    context.getEventProvider().add(constPeriodic);

    double simulationDuration = 10_000_000;
    SimulationModelImpl model = new SimulationModelImpl(simulationDuration, context);
    model.run();
  }

}
