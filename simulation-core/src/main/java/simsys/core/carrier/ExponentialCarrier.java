package simsys.core.carrier;

import simsys.random.ExponentialRandom;

import java.util.Random;

public class ExponentialCarrier implements Carrier<Double> {
    private ExponentialRandom exponentialRandom;

    public ExponentialCarrier(Random random, double rate) {
        this.exponentialRandom = new ExponentialRandom(random, rate);
    }

    @Override
    public Double getCarry() {
        return exponentialRandom.nextValue();
    }
}
