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
        return this.startOfRange + random.nextInt(Integer.MAX_VALUE) *
                (this.endOfRange - this.startOfRange) / Integer.MAX_VALUE;
    }

    // TODO: don't forget to clean up
    public static void main(String[] args) {

        int[] values = new int[11];
        RandomVariable rv = new UniformRV(new Random(), 0, 10);

        for(int i = 0; i < 10000; i++) {
//            System.out.println(rv.nextValue());
            values[(int) Math.round(rv.nextValue())]++;
        }

        for(int i: values) {
            System.out.println(i);
        }
    }

}
