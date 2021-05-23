package symsys.statistic;

import com.google.common.math.StatsAccumulator;
import javax.annotation.concurrent.NotThreadSafe;

/**
 * This object accumulates double values and tracks some basic statistics over all the values added
 * so far. The values may be added singly or in groups.
 */
@NotThreadSafe
public class StatisticAccumulator {

  private StatsAccumulator accumulator;

  public StatisticAccumulator() {
    accumulator = new StatsAccumulator();
  }

  public void addAll(double... values) {
    accumulator.addAll(values);
  }

  public void add(double value) {
    accumulator.add(value);
  }

  // TODO: дописать остальные
  public double mean() {
    return accumulator.mean();
  }
}
