package simsys.api.events;

public abstract class Event implements Comparable<Event>{

    /** The time when the event will be activated */
    protected double actionEventTime;
    /** Event activation */
    public abstract void actionEvent();

    public double getActionEventTime() {
        return actionEventTime;
    }

    @Override
    public int compareTo(Event event) {
        return Double.compare(getActionEventTime(), event.getActionEventTime());
    }
}