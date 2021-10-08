package simsys.random;


// we can use this RV for test purposes
public class DummyRandomVariable implements RandomVariable {

  private final double[] values;
  private int index;

  public DummyRandomVariable(double[] values) {
    index = -1;
    this.values = values;
  }

  @Override
  public double nextValue() {
    index = (index + 1) % values.length;
    return values[index];
  }
}
