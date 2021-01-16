package simsys.clock.imp;

import simsys.clock.api.Clock;

public final class ClockImp implements Clock {
    private static ClockImp instance = null;
    private double currentTime;

    private ClockImp() {
        this.currentTime = 0;
    }

    public static ClockImp getInstance() {
        if(instance == null) {
            instance = new ClockImp();
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

    @Override
    public void resetTime() {
        instance = null;
    }
}
