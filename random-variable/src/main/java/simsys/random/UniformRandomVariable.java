package simsys.random;

import java.util.concurrent.ThreadLocalRandom;

public class UniformRandomVariable implements RandomVariable {

  private final double startOfRange;
  private final double endOfRange;

  public UniformRandomVariable(double startOfRange, double endOfRange) {
    this.startOfRange = startOfRange;
    this.endOfRange = endOfRange;
  }

  @Override
  public double nextValue() {
    return startOfRange + ThreadLocalRandom.current().nextDouble() * (endOfRange - startOfRange);
  }

}
