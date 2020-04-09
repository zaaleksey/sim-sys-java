package simsys.examples.events;


import simsys.clock.Clock;
import simsys.clock.ClockImpl;
import simsys.core.condition.TimeStopCondition;
import simsys.core.environment.Environment;
import simsys.core.environment.EnvironmentImpl;
import simsys.core.event.HandledEvent;
import simsys.core.event.PeriodicEvent;
import simsys.core.event.handler.EventHandler;
import simsys.core.event.handler.TimerEventHandler;
import simsys.core.model.SimulationContext;
import simsys.core.model.SimulationContextImpl;
import simsys.core.model.SimulationModel;
import simsys.core.model.TimerSimulationModel;
import simsys.core.provider.EventProvider;
import simsys.core.provider.EventProviderImpl;

import java.util.Collections;
import java.util.Random;

public class TimerEventSimulation {

    public static void main(String[] args) {
        double maxTime = 2000;
        startTimer(maxTime);
    }

    public static void startTimer(double maxTime) {
        System.out.println("Timer simulation");
        getTimer(maxTime).run();
    }

    public static SimulationModel getTimer(double maxTime) {
        HandledEvent event = new PeriodicEvent(0);
        SimulationContext simulationContext = getContextWithOnlyOneEvent(event);
        return new TimerSimulationModel(simulationContext, new TimeStopCondition(maxTime));
    }

    public static SimulationContext getContextWithOnlyOneEvent(HandledEvent event) {
        Environment<?> environment = new EnvironmentImpl();
        Clock clock = ClockImpl.getInstance();
        EventProvider eventProvider = new EventProviderImpl(Collections.singleton(event));
        SimulationContext simulationContext = new SimulationContextImpl(environment, clock, eventProvider);
        EventHandler eventHandler = new TimerEventHandler(new Random(), 3, simulationContext);
        event.addHandler(eventHandler);
        return simulationContext;
    }
}
