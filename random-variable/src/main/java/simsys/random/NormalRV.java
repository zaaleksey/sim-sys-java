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
    return this.mean + this.sigma * random.nextGaussian();
  }

  //  TODO: don't forget to clean up
  public static void main(String[] args) {
    RandomVariable rv = new NormalRV(new Random(), 5, 1);

    for(int i = 0; i < 100; i++) {
      System.out.println(rv.nextValue());
    }
  }

}
