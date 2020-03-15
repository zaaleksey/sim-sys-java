package simsys.mm1;

public class StatisticsMM1 {
    private static double averageResponseTime = 0;
    private static int countOfDemands = 0;

    public static double getAverageResponseTime() {
        return averageResponseTime;
    }

    public static void setAverageResponseTime(double time) {
        averageResponseTime += time;
    }

    public static int getCountOfDemands() {
        return countOfDemands;
    }

    public static void increment() {
        countOfDemands++;
    }

    public static double getStatistics() {
        return averageResponseTime / countOfDemands;
    }
}
