package simsys.mm1;

public class SourceMM1 {
    public static DemandMM1 createDemand(double arrivingTime) {

        return new DemandMM1(arrivingTime);
    }
}
