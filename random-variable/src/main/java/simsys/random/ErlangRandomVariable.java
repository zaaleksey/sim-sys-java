package simsys.random;

import java.security.SecureRandom;
import java.util.stream.DoubleStream;

public class ErlangRandomVariable implements RandomVariable {

  private final int count;
  private final double rate;
  private final SecureRandom random;

  public ErlangRandomVariable(int count, double rate) {
    this.count = count;
    this.rate = rate;
    this.random = new SecureRandom();
  }

  @Override
  public double nextValue() {
    return -Math.log(getProductUniform01()) / rate;
  }

  private double getProductUniform01() {
    return DoubleStream.generate(random::nextDouble)
        .limit(count)
        .reduce(1, this::multiplication);
  }

  private double multiplication(double x, double y) {
    return x * y;
  }

}
