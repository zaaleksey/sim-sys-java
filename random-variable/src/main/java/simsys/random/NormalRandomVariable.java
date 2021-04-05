package simsys.random;

import java.util.Random;

public class NormalRandomVariable implements RandomVariable {

  private final Random random;
  private final double mean;
  private final double sigma;

  public NormalRandomVariable(Random random, double mean, double sigma) {
    this.random = random;
    this.mean = mean;
    this.sigma = sigma;
  }

  @Override
  public double nextValue() {
    return this.mean + this.sigma * random.nextGaussian();
  }

}
