package simsys.entity.queue;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import simsys.entity.demand.Demand;

@Slf4j
public class QueueFIFO implements Queue {

  private final int capacity;
  private final ArrayDeque<Demand> demandQueue;

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
  public int capacity() {
    return capacity;
  }

  @Override
  public boolean add(Demand demand) {
    Objects.requireNonNull(demand);
    if (size() < capacity) {
      demandQueue.add(demand);
      return true;
    }

    return false;
  }

  @Override
  public boolean addAll(Collection<Demand> demands) {
    Objects.requireNonNull(demands);
    for (Demand demand : demands) {
      if (!add(demand)) {
        LOGGER.debug("Queue overflow. The items were lost!");
        return false;
      }
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
