package simsys.random;

import java.util.Random;

public class NormalRV implements RandomVariable{

  private final Random random;
  private final double mean;
  private final double sigma;

  public NormalRV(Random random, double mean, double sigma) {
    this.random = random;
    this.mean = mean;
    this.sigma = sigma;
  }

  @Override
  public double nextValue() {
    return random.nextGaussian() * this.mean + this.sigma;
  }

}
