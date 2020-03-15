package simsys.api.events;

import java.util.List;

public abstract class Event implements Comparable<Event> {
    /** The time when the event will be activated */
    protected double actionEventTime;
    /** Event activation */
    public abstract List<Event> actionEvent();
    public double getActionEventTime() {
        return actionEventTime;
    }
    public void setActionEventTime(double actionEventTime) {
        this.actionEventTime = actionEventTime;
    }

    @Override
    public String toString() {
        return "" + actionEventTime;
    }

    @Override
    public int compareTo(Event event) {
        return Double.compare(actionEventTime, event.actionEventTime);
    }
}