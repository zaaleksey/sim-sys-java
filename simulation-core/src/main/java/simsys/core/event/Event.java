package simsys.core.event;

import org.jetbrains.annotations.NotNull;

public interface Event extends Comparable<Event> {

  double getActivateTime();

  void setActivateTime(double activateTime);

  default void postpone() {
    setActivateTime(Double.POSITIVE_INFINITY);
  }

  default double postpone(double duration) {
    setActivateTime(getActivateTime() + duration);
    return getActivateTime();
  }

  @Override
  default int compareTo(@NotNull Event event) {
    return Double.compare(this.getActivateTime(), event.getActivateTime());
  }

  void activate();
}
