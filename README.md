# sim-sys-java

[![CodeQL](https://github.com/AlexeyZavarzin/sim-sys-java/actions/workflows/codeql-analysis.yml/badge.svg)](https://github.com/AlexeyZavarzin/sim-sys-java/actions/workflows/codeql-analysis.yml)
[![Maven](https://github.com/AlexeyZavarzin/sim-sys-java/actions/workflows/maven.yml/badge.svg)](https://github.com/AlexeyZavarzin/sim-sys-java/actions/workflows/maven.yml)


____

## Discrete-event simulation framework

____

*Example*

Simple queuing system. The demand comes in with some intensity. Served if the device is free and
then leaves the system. If the system is busy, demand enters the waiting queue.

```java
  public static void simpleQueueingSystems() {
    Environment env = new EnvironmentImpl();
    Clock clock = new ClockImpl();
    EventProvider eventProvider = new EventProviderImpl(Collections.emptyList());
    SimulationContext context = new SimpleSimulationContext(env, clock, eventProvider);
    SimulationModelImpl model = new SimulationModelImpl(context);

    Queue queue = new QueueFIFO();
    Event event = createDemandEvent(2, queue, context);

    eventProvider.add(event, context.getCurrentTime());
    model.setStopCondition(new TimeStopCondition(100000));
    model.run();
  }
```

____

## Module

<!-- The modules that make up the system at the moment: -->
<!-- ![Module](https://github.com/AlexZavr/SimSysJava/raw/dev/documents/Module.png) -->

