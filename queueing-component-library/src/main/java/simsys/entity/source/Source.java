package simsys.entity.source;

import simsys.entity.demand.Demand;

public interface Source<T extends Demand> {
    T generateDemand();
}
