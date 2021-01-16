package simsys.system_components.imp.mm1;

import simsys.system_components.api.demand.Demand;

public class DemandMM1 extends Demand {
    public DemandMM1(double arrivalTime) {
        super();
        this.arrivalTime = arrivalTime;
        this.serviceStartTime = Double.POSITIVE_INFINITY;
        this.leavingTime = Double.POSITIVE_INFINITY;
    }
}
