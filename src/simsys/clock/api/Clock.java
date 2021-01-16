package simsys.clock.api;

public interface Clock {
    double getCurrentTime();
    void setCurrentTime(double currentTime);
    void resetTime();
}
