package simsys;

import simsys.api.random.RandomVariable;
import simsys.implementation.containers.QueueMM1;
import simsys.implementation.engine.EngineMM1;
import simsys.implementation.environment.Environment;
import simsys.implementation.random.ExponentialRandom;

import java.util.Random;

public class Run {

    public static void main(String[] args) {

        RandomVariable arrival = new ExponentialRandom(new Random(), 1);
        QueueMM1 eventContainer = new QueueMM1();
        Environment environment = Environment.createEnvironmetn(eventContainer);
        EngineMM1 engine = EngineMM1.createEngineMM1(environment, arrival);
        System.out.println("Start Simulation M/M/1");
        double simulationTime = 100000;
        engine.run(simulationTime);
    }
}