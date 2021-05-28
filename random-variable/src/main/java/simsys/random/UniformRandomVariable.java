package simsys.random;

import java.util.Random;

public class UniformRandomVariable implements RandomVariable {

  private final Random random;
  private final double startOfRange;
  private final double endOfRange;

  public UniformRandomVariable(Random random, double startOfRange, double endOfRange) {
    this.random = random;
    this.startOfRange = startOfRange;
    this.endOfRange = endOfRange;
  }

  @Override
  public double nextValue() {
    return this.startOfRange + random.nextDouble() * (this.endOfRange - this.startOfRange);
  }

}
