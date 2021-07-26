package simsys.core.model;

/**
 * Simulation model interface. The main logic of simulation is contained in the classes that
 * implement this interface.
 */
public interface SimulationModel {

  /**
   * The method responsible for running the simulation of the bottom model. The main simulation
   * cycle will be described in this method.
   */
  void run();

  /**
   * The step of the simulation model per unit of simulation time.
   */
  void step();

}
