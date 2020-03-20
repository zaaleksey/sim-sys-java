package old.implementation.random;

import old.api.random.RandomVariable;

import java.util.Random;

public class ExponentialRandom implements RandomVariable {
    private Random random;
    public double rate;

    public ExponentialRandom(Random random, double rate) {
        this.random = random;
        this.rate = rate;
    }

    @Override
    public double nextValue() {
        return -1.0 / rate * Math.log(random.nextDouble());
    }
}
