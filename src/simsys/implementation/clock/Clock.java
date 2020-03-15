package simsys.implementation.clock;

public class Clock {
    private static double currentTime = 0;

    public static double getCurrentTime() {
        return currentTime;
    }

    public static void setCurrentTime(double time) {
        currentTime = time;
    }
}
