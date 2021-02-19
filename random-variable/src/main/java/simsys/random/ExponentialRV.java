package simsys.random;

import java.util.Random;

public class ExponentialRV implements RandomVariable {

  private final Random random;
  private final double rate;

  public ExponentialRV(Random random, double rate) {
    this.random = random;
    this.rate = rate;
  }

  @Override
  public double nextValue() {
    return -1.0 / rate * Math.log(random.nextDouble());
  }

}
