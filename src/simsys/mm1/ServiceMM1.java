package simsys.mm1;

import simsys.api.random.RandomVariable;

public class ServiceMM1 {
    public static void serviceDemand(DemandMM1 demand, double serviceStartTime, RandomVariable care) {

        demand.setServiceStartTime(serviceStartTime);
        demand.setServiceCompletionTime(demand.getServiceStartTime() + care.nextValue());
    }
}
