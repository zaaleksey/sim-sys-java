package simsys.core.random;

import java.util.Random;

public class ExponentialRandom implements RandomVariable {
    public double rate;
    private Random random;

    public ExponentialRandom(Random random, double rate) {
        this.random = random;
        this.rate = rate;
    }

    @Override
    public double nextValue() {
        return -1.0 / rate * Math.log(random.nextDouble());
    }
}
