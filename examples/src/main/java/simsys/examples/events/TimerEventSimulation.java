package simsys.examples.events;


import com.google.common.base.Strings;
import simsys.core.clock.Clock;
import simsys.core.clock.ClockImpl;
import simsys.core.condition.TimeStopCondition;
import simsys.core.environment.Environment;
import simsys.core.environment.EnvironmentImpl;
import simsys.core.event.HandledEvent;
import simsys.core.event.handler.Timeout;
import simsys.core.model.SimulationContext;
import simsys.core.model.SimulationContextImpl;
import simsys.core.model.SimulationModelImpl;
import simsys.core.provider.EventProvider;
import simsys.core.provider.EventProviderImpl;
import simsys.random.ExponentialRandomVariable;

import java.util.Collections;
import java.util.Random;

public class TimerEventSimulation {

    public static void simpleExample() {
        //Должна быть фабрика, которая создает это все
        Environment env = new EnvironmentImpl();
        Clock clock = new ClockImpl();
        EventProvider eventProvider = new EventProviderImpl(Collections.emptyList());
        SimulationContext simulationContext = new SimulationContextImpl(env, clock, eventProvider);

        //После того как создан контекст мы можем создать другую фабрику, которая будет  инжектить этот контекст
        // во все классы и билдеры, если это необходимо
        //не хочется самому таскать все зависимости, а simulationContext это основная зависимость

        SimulationModelImpl model = new SimulationModelImpl(simulationContext);

        //То есть будет фабрика, которая вернет билдер для события, этот билдер уже содержит контекст
        //мы только докручиваем остальные поля и вызываем метод build для создания объекта

        HandledEvent periodic = new HandledEvent();
        //фабрика для события должна позволить строить сразу периодическое событие (не будем делать такое событие через
        // наследование, не надо плодить лишнюю иерархию, событие можно делать периодическим через добавление в него
        //специального хендлера timeout (в хендлер тож надо инжектить context автоматически).
        Timeout timeout = new Timeout(new ExponentialRandomVariable(new Random(), 1));
        timeout.setSimulationContext(simulationContext);


        periodic.addHandler(timeout);
        eventProvider.add(periodic);
        model.setStopCondition(new TimeStopCondition(100));
        model.run();
    }

    public static void builderExample() {
        Environment env = new EnvironmentImpl();
        Clock clock = new ClockImpl();
        EventProvider eventProvider = new EventProviderImpl(Collections.emptyList());
        SimulationContext simulationContext = new SimulationContextImpl(env, clock, eventProvider);

        HandledEvent randomPeriodic = new HandledEvent.HandledEventBuilder(simulationContext)
                .periodic(new ExponentialRandomVariable(new Random(), 1))
                .addHandler(event -> System.out.println("Message from periodic random event: " + event.getActivateTime()))
                .build();

        HandledEvent constPeriodic = new HandledEvent.HandledEventBuilder(simulationContext)
                .periodic(3)
                .addHandler(event -> System.out.println("Message from periodic const event: " + event.getActivateTime()))
                .build();

        eventProvider.add(randomPeriodic);
        eventProvider.add(constPeriodic);


        SimulationModelImpl model = new SimulationModelImpl(simulationContext);
        model.setStopCondition(new TimeStopCondition(10));
        model.run();
    }

    public static void main(String[] args) {
        //simpleExample();


        System.out.println(Strings.repeat("#", 50));

        builderExample();

    }
}
