package simsys.entity.queue;

import java.util.ArrayList;
import java.util.Collection;
import simsys.core.model.AbstractSimulationModel;
import simsys.entity.demand.Demand;

public class QueueImpl extends AbstractSimulationModel implements Queue {

  private ArrayList<Demand> demandQueue;

  public QueueImpl() {
    this.demandQueue = new ArrayList<>();
  }

  public QueueImpl(Collection<? extends Demand> demands) {
    this.demandQueue = new ArrayList<>();
    this.demandQueue.addAll(demands);
  }

  @Override
  public int size() {
    return demandQueue.size();
  }

  @Override
  public void add(Demand demand) {
    demandQueue.add(demand);
  }

  @Override
  public void addAll(Collection<Demand> demands) {
    demandQueue.addAll(demands);
  }

  @Override
  public Demand peek() {
    return demandQueue.get(0);
  }

  @Override
  public Demand poll() {
    Demand demand = peek();
    demandQueue.remove(0);
    return demand;
  }
}
