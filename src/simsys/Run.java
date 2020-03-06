package simsys;

import org.apache.log4j.BasicConfigurator;
import simsys.api.containers.EventManager;
import simsys.api.engine.Engine;
import simsys.api.events.Event;
import simsys.api.random.RandomVariable;
import simsys.implementation.containers.EventManagerMM1;
import simsys.implementation.engine.EngineMM1;
import simsys.implementation.environment.Environment;
import simsys.implementation.events.comparators.ActionTimeAndPriorityComp;
import simsys.implementation.random.ExponentialRandom;
import simsys.mm1.StatisticsMM1;

import java.util.Comparator;
import java.util.Random;

public class Run {

    public static void main(String[] args) {

        BasicConfigurator.configure();

        double lambda = 0.5;
        double mu = 1.0;

        Random random = new Random();
        RandomVariable arrival = new ExponentialRandom(random, lambda);
        RandomVariable care = new ExponentialRandom(random, mu);

        Comparator<Event> comparator = new ActionTimeAndPriorityComp();
        EventManager container = new EventManagerMM1(comparator);

        Environment environment = Environment.createEnvironment(container);
        Engine engine = new EngineMM1(environment);

        double simulationTime = 1000;
        engine.run(simulationTime);

        System.out.println(StatisticsMM1.getStatistics());
    }
}