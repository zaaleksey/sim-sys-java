# sim-sys-java

[![Build](https://github.com/AlekseyZavarzin/sim-sys-java/actions/workflows/build.yml/badge.svg)](https://github.com/AlexeyZavarzin/sim-sys-java/actions/workflows/build.yml)
[![SonarCloud](https://github.com/AlekseyZavarzin/sim-sys-java/actions/workflows/sonar.yml/badge.svg)](https://github.com/AlexeyZavarzin/sim-sys-java/actions/workflows/sonar.yml)
[![CodeQL](https://github.com/AlekseyZavarzin/sim-sys-java/actions/workflows/codeql-analysis.yml/badge.svg)](https://github.com/AlexeyZavarzin/sim-sys-java/actions/workflows/codeql-analysis.yml)


____

# Discrete-event simulation framework

____

## Description

The framework’s engine is based on an Event-Driven Architecture. 
The framework allows to build and configure simple models of real systems, conduct experiments, collect and analyze statistical data. 
Also, this framework contains a module allowing to build models of systems and networks from the Queueing Theory. 

____

## Module
The framework contains the following modules

![Modele](https://github.com/AlekseyZavarzin/sim-sys-java/blob/master/documents/Module.png?raw=true)
____

## Example

### Event Handler

Implementing a simple timer through an event (HandledEvent) and its handler (TimeoutHandler). 

The timer is activated at intervals that are distributed exponentially with some rate.

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

### Agent based simulation

This is an example of a Queuing System type MM1. 

![Modele](https://github.com/AlekseyZavarzin/sim-sys-java/blob/master/documents/NetworkConfigurationExample.png?raw=true)

Arrivals occur at rate λ according to a Poisson process. 
Service times are exponentially distributed with rate parameter μ so that 1/μ is the mean service time. 
A single server serves customers one at a time from the front of the queue,
according to a first-come, first-served discipline. 
When the service is complete the customer
leaves the queue and the number of customers in the system reduces by one.

Implementation through agent-based modeling.

```java
public class AgentSimulationMM1 {
  public static void main(String[] args) {
    double simulationDuration = 10_000_000;
    SimulationContext context = SimulationContextImpl.getContext();
    double lambda = 1;
    SourceAgent source = new SourceAgent(context,
        new ExponentialRandomVariable(lambda),
        "Source");

    double mu = 2;
    Queue queue = new QueueFIFO();
    QueueingSystemAgent queueingSystem = new QueueingSystemAgent(context, queue,
        new ExponentialRandomVariable(mu),
        "Server"
    );

    source.setReceiver(queueingSystem);

    AgentBasedSimulationModel agentSimulationMM1 = new AgentBasedSimulationModel(simulationDuration,
        context);
    agentSimulationMM1.addAgents(Arrays.asList(source, queueingSystem));
    source.sendDemand();

    long startTime = System.nanoTime();
    agentSimulationMM1.run();
    long endTime = System.nanoTime();
    long timeElapsed = endTime - startTime;
    System.out.println("Elapsed time = " + (double) (timeElapsed) / 1_000_000_000);

    // correct answer  = 1/(mu - lambda) = 1
    System.out.println("Average service time is "
        + queueingSystem.getStatisticForVariable("SOJOURN_TIME").mean());
  }
}
```
____
