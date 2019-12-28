package simsys.mm1;

public class StatisticsMM1 {

    private static double averegeResponseTime = 0;
    private static int countOfDemands = 0;

    public static double getAveregeResponseTime() {
        return averegeResponseTime;
    }

    public static void setAveregeResponseTime(double time) {
        averegeResponseTime += time;
    }

    public static int getCountOfDemands() {
        return countOfDemands;
    }

    public static void increment() {
        countOfDemands++;
    }

    public static double getStatistics() {
        return averegeResponseTime / countOfDemands;
    }
}
