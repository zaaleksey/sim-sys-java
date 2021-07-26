package simsys.random;

import java.util.concurrent.ThreadLocalRandom;

public class NormalRandomVariable implements RandomVariable {

  private final double mean;
  private final double sigma;

  public NormalRandomVariable(double mean, double sigma) {
    this.mean = mean;
    this.sigma = sigma;
  }

  @Override
  public double nextValue() {
    return mean + sigma * ThreadLocalRandom.current().nextGaussian();
  }

}
