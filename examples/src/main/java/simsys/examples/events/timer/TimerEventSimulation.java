package simsys.examples.events.timer;


import simsys.core.condition.TimeStopCondition;
import simsys.core.context.SimulationContext;
import simsys.core.context.SimulationContextImpl;
import simsys.core.event.HandledEvent;
import simsys.core.event.handler.TimeoutHandler;
import simsys.core.model.SimulationModelImpl;
import simsys.random.ExponentialRandomVariable;

/**
 * Implementing a simple timer through a event (HandledEvent) and its handler (TimeoutHandler). The
 * timer is activated at intervals that are distributed exponentially with some rate.
 */
public class TimerEventSimulation {

  public static void main(String[] args) {
    SimulationContext context = SimulationContextImpl.getContext();

    HandledEvent periodic = new HandledEvent();
    TimeoutHandler timeout = new TimeoutHandler(
        new ExponentialRandomVariable(1));
    timeout.setSimulationContext(context);

    periodic.addHandler(event ->
        System.out.println("Message from periodic event: " + event.getActivateTime()));
    periodic.addHandler(timeout);

    context.getEventProvider().add(periodic);

    SimulationModelImpl model = new SimulationModelImpl(context);
    model.setStopCondition(new TimeStopCondition(1_000));
    model.run();
  }

}
