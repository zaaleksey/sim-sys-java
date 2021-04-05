package simsys.core.clock;

/**
 * Интерфейс объекта часов времени в имитационной модели.
 * Time clock object interface in the simulation model.
 */
public interface Clock {

  /**
   * Предоставляет время наступления предшествующего события (для расчета дельты).
   * Provides the time of the preceding event (for calculating the delta).
   *
   * @return время предшествующего события / time of the previous event
   */
  double getPreviousTime();

  /**
   * Предоставляет текущее время имитационной модели.
   * Provides the current time of the simulation model.
   *
   * @return текущее время / current time
   */
  double getCurrentTime();

  /**
   * Устанавливает текущее время имитационной модели в заданное время.
   * Sets the current time of the simulation model at a given time.
   *
   * @param currentTime - новое текущее время в модели / new current time in the model
   */
  void setCurrentTime(double currentTime);

}
