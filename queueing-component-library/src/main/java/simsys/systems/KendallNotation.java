package simsys.systems;


import simsys.entity.queue.Queue;
import simsys.random.RandomVariable;

/**
 * This class defines modified Kendall's notation for queueing systems
 */
public class KendallNotation extends QSDescription {

  private RandomVariable interArrivalTimes;

  public KendallNotation(RandomVariable interArrivalTimes, Integer countOfServers,
      RandomVariable serviceTime, Queue queue) {
    super(countOfServers, serviceTime, queue);
    this.interArrivalTimes = interArrivalTimes;
  }
}
