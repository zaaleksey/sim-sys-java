package simsys.examples.events;


import simsys.core.clock.Clock;
import simsys.core.clock.ClockImpl;
import simsys.core.environment.Environment;
import simsys.core.event.HandledEvent;
import simsys.core.event.handler.EventHandler;
import simsys.core.model.SimulationContext;
import simsys.core.model.SimulationContextImpl;
import simsys.core.model.SimulationModelImpl;
import simsys.core.provider.EventProvider;
import simsys.core.provider.EventProviderImpl;

import java.util.ArrayList;
import java.util.TreeSet;

public class TimerEventSimulation {


    public static void main(String[] args) {

        System.out.println("Timer simulation");

        //Написать таймер: единственное повторяемое событие обработчик которого выводит на экран текущее время
        //условие останова - достижение некоторого момента времени
        Environment environment = new Environment() {
            @Override
            public void addEntity(Object entity, String id) {

            }

            @Override
            public Object getEntity(String id) {
                return null;
            }
        };
        Clock clock = ClockImpl.getInstance();
        EventProvider eventProvider = new EventProviderImpl(new ArrayList<HandledEvent>());
        SimulationContext simulationContext = new SimulationContextImpl(environment, clock, eventProvider);
        SimulationModelImpl timer = new SimulationModelImpl(simulationContext);

        timer.run();
    }
}
