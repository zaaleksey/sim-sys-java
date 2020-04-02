package simsys.examples.events;


import simsys.core.clock.Clock;
import simsys.core.clock.ClockImpl;
import simsys.core.environment.Environment;
import simsys.core.environment.EnvironmentImpl;
import simsys.core.event.HandledEvent;
import simsys.core.event.handler.TimerEventHandler;
import simsys.core.model.SimulationContext;
import simsys.core.model.SimulationContextImpl;
import simsys.core.model.SimulationModelImpl;
import simsys.core.provider.EventProvider;
import simsys.core.provider.EventProviderImpl;

import java.util.Arrays;
import java.util.List;

public class TimerEventSimulation {


    public static void main(String[] args) {

        System.out.println("Timer simulation");

        //Написать таймер: единственное повторяемое событие обработчик которого выводит на экран текущее время
        //условие останова - достижение некоторого момента времени
        Environment environment = new EnvironmentImpl();
        Clock clock = ClockImpl.getInstance();

        HandledEvent event = new HandledEvent();
        event.setActivateTime(0);
        event.addHandler(new TimerEventHandler());
        List<HandledEvent> eventList = Arrays.asList(event);
        EventProvider eventProvider = new EventProviderImpl(eventList);

        SimulationContext simulationContext = new SimulationContextImpl(environment, clock, eventProvider);
        SimulationModelImpl timer = new SimulationModelImpl(simulationContext);

        timer.run();
    }
}
