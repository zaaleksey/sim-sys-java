package simsys.entity.queue;

import java.util.ArrayDeque;
import java.util.Collection;
import simsys.entity.demand.Demand;

public class QueueFIFO implements Queue {

  private ArrayDeque<Demand> demandQueue;

  public QueueFIFO() {
    this.demandQueue = new ArrayDeque<>();
  }

  public QueueFIFO(Collection<? extends Demand> demands) {
    this.demandQueue = new ArrayDeque<>();
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
    return demandQueue.peek();
  }

  @Override
  public Demand remove() {
    return demandQueue.remove();
  }

}
