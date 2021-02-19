package simsys.random;

public class UniformRV implements RandomVariable {

    private final double startOfRange;
    private final double endOfRange;

    public UniformRV(double startOfRange, double endOfRange) {

        this.startOfRange = startOfRange;
        this.endOfRange = endOfRange;
    }

    @Override
    public double nextValue() {
        return 1 / (this.endOfRange - this.startOfRange);
    }

}
