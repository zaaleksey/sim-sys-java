package simsys.entity.queue;

import java.util.Collection;
import simsys.entity.demand.Demand;

public interface Queue {

  int size();

  void add(Demand demand);

  void addAll(Collection<Demand> demands);

  Demand peek();

  Demand remove();

  default boolean isEmpty() {
    return size() == 0;
  }

}
