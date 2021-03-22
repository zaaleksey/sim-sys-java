package simsys.systems;


import simsys.entity.queue.Queue;
import simsys.random.RandomVariable;

/**
 * This class defines a queueing system.
 */
public class QSDescription {

  private Integer countOfServers;
  private RandomVariable serviceTime;
  private Queue queue;

  public QSDescription(Integer countOfServers,
      RandomVariable serviceTime, Queue queue) {
    this.countOfServers = countOfServers;
    this.serviceTime = serviceTime;
    this.queue = queue;
  }

  public Integer getCountOfServers() {
    return countOfServers;
  }

  public void setCountOfServers(Integer countOfServers) {
    this.countOfServers = countOfServers;
  }

  public RandomVariable getServiceTime() {
    return serviceTime;
  }

  public void setServiceTime(RandomVariable serviceTime) {
    this.serviceTime = serviceTime;
  }

  public Queue getQueue() {
    return queue;
  }

  public void setQueue(Queue queue) {
    this.queue = queue;
  }

}
