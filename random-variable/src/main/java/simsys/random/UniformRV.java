package simsys.random;

import java.util.Random;

public class UniformRV implements RandomVariable {

    private final Random random;
    private final double startOfRange;
    private final double endOfRange;

    public UniformRV(Random random, double startOfRange, double endOfRange) {
        this.random = random;
        this.startOfRange = startOfRange;
        this.endOfRange = endOfRange;
    }

    @Override
    public double nextValue() {
        return this.startOfRange + random.nextDouble() * (this.endOfRange - this.startOfRange);
    }

    // TODO: don't forget to clean up
    public static void main(String[] args) {

        int n = 10;
        int[] counter = new int[n];
        RandomVariable rv = new UniformRV(new Random(), 0, n);

        for(int i = 0; i < 1000000; i++) {
            counter[(int) rv.nextValue()]++;
        }

        for(int i = 0; i < n; i++) {
            System.out.println(i + ": " + counter[i]);
        }
    }

}
