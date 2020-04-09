package simsys.clock;

public final class ClockImpl implements Clock {
    private double currentTime;
    private static ClockImpl instance = null;

    private ClockImpl() {
        this.currentTime = 0;
    }

    public static ClockImpl getInstance() {
        if(instance == null) {
            instance = new ClockImpl();
        }
        return instance;
    }

    @Override
    public double getCurrentTime() {
        return this.currentTime;
    }

    @Override
    public void setCurrentTime(double currentTime) {
        this.currentTime = currentTime;
    }
}
