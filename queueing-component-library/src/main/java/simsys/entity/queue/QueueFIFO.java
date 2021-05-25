package simsys.entity.queue;

import simsys.entity.demand.Demand;

import java.util.ArrayDeque;
import java.util.Collection;

public class QueueFIFO implements Queue {

  private int capacity;
  private ArrayDeque<Demand> demandQueue;

  public QueueFIFO() {
    this.capacity = Integer.MAX_VALUE;
    this.demandQueue = new ArrayDeque<>();
  }

  public QueueFIFO(int capacity) {
    this.capacity = capacity;
    this.demandQueue = new ArrayDeque<>();
  }

  public QueueFIFO(Collection<? extends Demand> demands) {
    this.capacity = Integer.MAX_VALUE;
    this.demandQueue = new ArrayDeque<>();
    this.demandQueue.addAll(demands);
  }

  public QueueFIFO(int capacity, Collection<? extends Demand> demands) {
    this.capacity = capacity;
    this.demandQueue = new ArrayDeque<>();
    this.demandQueue.addAll(demands);
  }

  @Override
  public int size() {
    return demandQueue.size();
  }

  @Override
  public boolean add(Demand demand) {
    if (size() <= capacity) {
      demandQueue.add(demand);
      return true;
    }
    return false;
  }

  @Override
  public boolean addAll(Collection<Demand> demands) {
    for (Demand demand : demands) {
      if (!add(demand)) return false;
    }
    return true;
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
