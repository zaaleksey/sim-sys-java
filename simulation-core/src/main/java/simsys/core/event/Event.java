package simsys.core.event;

import org.jetbrains.annotations.NotNull;

/**
 * The interface of events that occur in the simulation model. Implements the Comparable interface
 * that allows you to compare events with each other by the time of their occurrence.
 */
public interface Event extends Comparable<Event> {

  /**
   * Returns the activation time of this event.
   *
   * @return the activation time of this event
   */
  double getActivateTime();

  /**
   * Sets the activation time for this event.
   *
   * @param activateTime the event activation time
   */
  void setActivateTime(double activateTime);

  /**
   * Event activation method. Describes the logic for handling the event.
   */
  void activate();

  /**
   * Delays activation of an event indefinitely. Sets the activation time of this event to {@code
   * Double.POSITIVE_INFINITY}.
   */
  default void postpone() {
    setActivateTime(Double.POSITIVE_INFINITY);
  }

  /**
   * Delays the activation of this event for a while. The activation delay time is transmitted by
   * the parameter. Returns the new activation time for this event.
   *
   * @param duration the delay time for the activation of this event
   * @return the new activation time for this event
   */
  default double postpone(double duration) {
    setActivateTime(getActivateTime() + duration);
    return getActivateTime();
  }

  /**
   * Comparison of events by their activation time.
   *
   * @param event the event to be compared
   * @return a negative integer, zero, or a positive integer as this event is less than, equal to,
   * or greater than the specified event.
   */
  @Override
  default int compareTo(@NotNull Event event) {
    return Double.compare(this.getActivateTime(), event.getActivateTime());
  }

}
