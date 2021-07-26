package simsys.random;

import java.util.concurrent.ThreadLocalRandom;

public class ErlangRandomVariable implements RandomVariable {

  private final int count;
  private final double rate;

  public ErlangRandomVariable(int count, double rate) {
    this.count = count;
    this.rate = rate;
  }

  @Override
  public double nextValue() {
    return -Math.log(getProductUniform01()) / rate;
  }

  private double getProductUniform01() {
    double product = 1;
    double[] arr = new double[count];

    for (int i = 0; i < count; i++) {
      arr[i] = ThreadLocalRandom.current().nextDouble();
    }

    for (int i = 0; i < count; i++) {
      product *= arr[i];
    }

    return product;
  }

}
