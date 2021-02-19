package simsys.component;

import simsys.entity.demand.Demand;
import simsys.entity.demand.SimpleDemand;

public class AbstractSourceImpl extends AbstractSource {

  public Demand createDemand() {
    return new SimpleDemand(0);
  }

  @Override
  public void arrivalAction() {
    System.out.println("Arrival Action - here we need create a demand, send this to a node");
    sleep(interArrivalTime());
  }

  @Override
  public double interArrivalTime() {
    return 0;
  }

}
