package simsys;

import simsys.engine.SimulationEngine;

public class Demo {

    public static void main(String[] args) {

        SimulationEngine engine = new SimulationEngine();
        System.out.println("Start Simulation");
        engine.run();
    }
}
