package simsys.event_sets.api;

import java.util.List;

public abstract class Event implements Comparable<Event> {
    protected double actionTime;

    public double getActionTime() {
        return this.actionTime;
    }

    public void setActionTime(double actionTime) {
        this.actionTime = actionTime;
    }

    public abstract List<Event> actionEvent();

    @Override
    public int compareTo(Event event) {
        return Double.compare(this.actionTime, event.getActionTime());
    }
}
