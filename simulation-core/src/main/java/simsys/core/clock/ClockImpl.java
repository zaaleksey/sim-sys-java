package simsys.core.clock;

public final class ClockImpl implements Clock {
    private double currentTime;

    private ClockImpl() {
        this.currentTime = 0;
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
