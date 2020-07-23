# sim-sys-java

____

## Discrete-event simulation framework

____

*Example*
Simple queuing system. The demand comes in with some intensity. Served if the device is free and then leaves the system. If the system is busy, demand enters the waiting queue.

```java
  ____
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
  ____
```
