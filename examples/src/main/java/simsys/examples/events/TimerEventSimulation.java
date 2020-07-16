package simsys.examples.events;


import com.google.common.base.Strings;
import java.util.Collections;
import java.util.Random;

import lombok.SneakyThrows;
import simsys.core.clock.Clock;
import simsys.core.clock.ClockImpl;
import simsys.core.condition.TimeStopCondition;
import simsys.core.context.SimpleSimulationContext;
import simsys.core.context.SimulationContext;
import simsys.core.environment.Environment;
import simsys.core.environment.EnvironmentImpl;
import simsys.core.event.HandledEvent;
import simsys.core.event.HandledEventBuilderFactory;
import simsys.core.event.handler.TimeoutHandler;
import simsys.core.model.SimulationModelImpl;
import simsys.core.provider.EventProvider;
import simsys.core.provider.EventProviderImpl;
import simsys.random.ExponentialRV;

public class TimerEventSimulation {

  @SneakyThrows
  public static void simpleExample() {
    // Должна быть фабрика, которая создает это все
    Environment env = new EnvironmentImpl();
    Clock clock = new ClockImpl();
    EventProvider eventProvider = new EventProviderImpl(Collections.emptyList());
    SimulationContext simulationContext = new SimpleSimulationContext(env, clock, eventProvider);

    // После того как создан контекст мы можем создать другую фабрику, которая будет  инжектить этот контекст
    // во все классы и билдеры, если это необходимо
    // не хочется самому таскать все зависимости, а simulationContext это основная зависимость

    SimulationModelImpl model = new SimulationModelImpl(simulationContext);

    // То есть будет фабрика, которая вернет билдер для события, этот билдер уже содержит контекст
    // мы только докручиваем остальные поля и вызываем метод build для создания объекта

    HandledEvent periodic = new HandledEvent();
    // Фабрика для события должна позволить строить сразу периодическое событие (не будем делать такое событие через
    // наследование, не надо плодить лишнюю иерархию, событие можно делать периодическим через добавление в него
    // специального хендлера timeout (в хендлер тож надо инжектить context автоматически).
    TimeoutHandler timeout = new TimeoutHandler(new ExponentialRV(new Random(), 1));
    timeout.setSimulationContext(simulationContext);

    periodic.addHandler(timeout);
    eventProvider.add(periodic, clock.getCurrentTime());
    model.setStopCondition(new TimeStopCondition(10));
    model.run();
  }


  @SneakyThrows
  public static void factoryExample() {
    Environment env = new EnvironmentImpl();
    Clock clock = new ClockImpl();
    EventProvider eventProvider = new EventProviderImpl(Collections.emptyList());
    SimulationContext simulationContext = new SimpleSimulationContext(env, clock, eventProvider);

    HandledEventBuilderFactory eventBuilderFactory = new HandledEventBuilderFactory(
        simulationContext,
        event -> System.out.println("Before handler for event " + event),
        event -> System.out.println("After handler for event " + event));

    HandledEvent randomPeriodic = eventBuilderFactory
        .create()
        .periodic(new ExponentialRV(new Random(), 1))
        .addHandler(event -> System.out
            .println("Message from periodic random event: " + event.getActivateTime()))
        .build();

    HandledEvent constPeriodic = eventBuilderFactory
        .create()
        .periodic(3)
        .addHandler(event -> System.out
            .println("Message from periodic const event: " + event.getActivateTime()))
        .build();

    eventProvider.add(randomPeriodic, clock.getCurrentTime());
    eventProvider.add(constPeriodic, clock.getCurrentTime());

    SimulationModelImpl model = new SimulationModelImpl(simulationContext);
    model.setStopCondition(new TimeStopCondition(10));
    model.run();
  }

  public static void main(String[] args) {
    simpleExample();

    System.out.println(Strings.repeat("#", 50));

    factoryExample();
  }
}
