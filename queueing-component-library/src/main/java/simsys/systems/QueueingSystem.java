package simsys.systems;

import simsys.entity.demand.Demand;

public interface QueueingSystem {

  void arrive(Demand demand);
  void startService(Demand demand);
  void endService(Demand demand);

}
