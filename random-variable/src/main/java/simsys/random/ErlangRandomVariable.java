package simsys.random;

import java.util.Random;

public class ErlangRandomVariable implements RandomVariable {

  private final Random random;
  private final int count;
  private final double rate;

  public ErlangRandomVariable(Random random, int count, double rate) {
    this.random = random;
    this.count = count;
    this.rate = rate;
  }

  @Override
  public double nextValue() {
    return -Math.log(getProductUniform01()) / this.rate;
  }

  private double getProductUniform01() {
    double product = 1;
    double[] arr = new double[this.count];

    for (int i = 0; i < this.count; i++) {
      arr[i] = this.random.nextDouble();
    }

    for (int i = 0; i < this.count; i++) {
      product *= arr[i];
    }

    return product;
  }

}
