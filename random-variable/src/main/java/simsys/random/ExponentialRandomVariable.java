package simsys.random;

import java.util.concurrent.ThreadLocalRandom;

public class ExponentialRandomVariable implements RandomVariable {

  private final double rate;

  public ExponentialRandomVariable(double rate) {
    this.rate = rate;
  }

  @Override
  public double nextValue() {
    return -1.0 / rate * Math.log(ThreadLocalRandom.current().nextDouble());
  }

}
