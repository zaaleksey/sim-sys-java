package simsys;

import simsys.implementation.engine.SimulationEngine;
import simsys.implementation.environment.Environment;
import simsys.implementation.containers.DemoEventContainer;

public class Demo {

    public static void main(String[] args) {

        DemoEventContainer eventContainer = new DemoEventContainer();
        Environment environment = new Environment(eventContainer);
        SimulationEngine engine = new SimulationEngine(environment);
        System.out.println("Start Simulation");
        engine.run();
    }
}
