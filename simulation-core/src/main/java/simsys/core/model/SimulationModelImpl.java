package simsys.core.model;

import simsys.core.context.SimulationContext;

/**
 * Basic implementation of the simulation model. Inherits the abstract class {@code
 * AbstractSimulationModel}. Nothing new is added.
 */
public class SimulationModelImpl extends AbstractSimulationModel {

  /**
   * @param simulationDuration total simulation time of the model
   * @param simulationContext  simulation context
   */
  public SimulationModelImpl(double simulationDuration, SimulationContext simulationContext) {
    super(simulationDuration);
    this.simulationContext = simulationContext;
  }

}
