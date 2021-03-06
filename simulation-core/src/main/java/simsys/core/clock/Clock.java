package simsys.core.clock;

public interface Clock {

  double getPreviousTime();
  double getCurrentTime();
  void setCurrentTime(double currentTime);

}
