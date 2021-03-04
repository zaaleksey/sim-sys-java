package simsys.random;

import java.util.Random;

public class ErlangRV implements RandomVariable {

  private final Random random;
  private final int n;
  private final double rate;

  public ErlangRV(Random random, int n, double rate) {
    this.random = random;
    this.n = n;
    this.rate = rate;
  }

  @Override
  public double nextValue() {
    return -Math.log(getProductUniform01()) / this.rate;
  }

  private double getProductUniform01() {
    double product = 1;
    double[] arr = new double[this.n];

    for(int i = 0; i < this.n; i++) {
      arr[i] = this.random.nextDouble();
    }

    for (int i = 0; i < this.n; i++) {
      product *= arr[i];
    }

    return product;
  }

}
