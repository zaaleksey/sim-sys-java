package simsys.api.events;

import java.util.Comparator;

public abstract class Event implements Comparable<Event>{

    /** The time when the event will be activated */
    protected double actionEventTime;

    /** Event activation */
    public abstract void actionEvent();

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

    public static Comparator<Event> actionTimeComparator = new Comparator<Event>() {

        @Override
        public int compare(Event event1, Event event2) {
            return event1.compareTo(event2);
        }
    };
}