package simsys.core.clock;

/**
 * Основная реализация интерфейса Clock.
 */
public final class ClockImpl implements Clock {

  private double previousTime;
  private double currentTime;

  public ClockImpl() {
    this.previousTime = 0;
    this.currentTime = 0;
  }

  @Override
  public double getPreviousTime() {
    return this.previousTime;
  }

  @Override
  public double getCurrentTime() {
    return this.currentTime;
  }

  @Override
  public void setCurrentTime(double currentTime) {
    this.previousTime = this.currentTime;
    this.currentTime = currentTime;
  }

}
