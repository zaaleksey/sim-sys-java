package simsys.core.model;

import simsys.core.context.SimulationContext;

/**
 * Basic implementation of the simulation model. Inherits the abstract class {@code
 * AbstractSimulationModel}. Nothing new is added.
 */
public class SimulationModelImpl extends AbstractSimulationModel {

  /**
   * @param simulationContext simulation context
   */
  public SimulationModelImpl(SimulationContext simulationContext) {
    this.simulationContext = simulationContext;
  }

}
