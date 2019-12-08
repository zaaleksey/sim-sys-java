package simsys;

import simsys.api.containers.EventContainer;
import simsys.api.engine.Engine;
import simsys.api.events.Event;
import simsys.api.random.RandomVariable;
import simsys.implementation.containers.EventContainerMM1;
import simsys.implementation.engine.EngineMM1;
import simsys.implementation.environment.Environment;
import simsys.mm1.InfoMM1;
import simsys.implementation.random.ExponentialRandom;

import java.util.Comparator;
import java.util.Random;

public class Run {

    public static void main(String[] args) {

        double lambda = 1.0;
        double mu = 2.0;

        RandomVariable arrival = new ExponentialRandom(new Random(), lambda);
        RandomVariable care = new ExponentialRandom(new Random(), mu);

        InfoMM1 infoMM1 = InfoMM1.INFO;
        infoMM1.setArrivalRandom(arrival);
        infoMM1.setCareRandom(care);

        Comparator<Event> comparator = Event.actionTimeComparator;
        EventContainer container = new EventContainerMM1(comparator);

        Environment environment = Environment.createEnvironmetn(container);
        Engine engine = new EngineMM1(environment, infoMM1);

        System.out.println("Start Simulation M/M/1");
        double simulationTime = 100000;
        engine.run(simulationTime);
    }
}