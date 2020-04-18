package simsys.random;

import java.util.Random;

public class ExponentialRandomVariable implements RandomVariable {
    public double rate;
    private final Random random;

    public ExponentialRandomVariable(Random random, double rate) {
        this.random = random;
        this.rate = rate;
    }

    @Override
    public double nextValue() {
        return -1.0 / rate * Math.log(random.nextDouble());
    }
}
