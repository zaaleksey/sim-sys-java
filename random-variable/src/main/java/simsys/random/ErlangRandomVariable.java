package simsys.random;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.DoubleStream;

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
    return DoubleStream.generate(ThreadLocalRandom.current()::nextDouble)
        .limit(count)
        .reduce(1, this::multiplication);
  }

  private double multiplication(double x, double y) {
    return x * y;
  }

}
