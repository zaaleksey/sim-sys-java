package symsys.statistic;

/**
 * This object calculates some basic statistics for the entire set of values.
 */
public class StatisticCalculation {

  private StatisticCalculation() {
  }

  /**
   * Returns the arithmetic mean of the values. The count must be non-zero.
   * @param values - a series of values
   * @return the arithmetic mean of the values
   */
  public double mean(double... values) {
    return getSumOfValues(values) / values.length;
  }

  /**
   * Returns the standard deviation. Measure of the amount of variation or dispersion of a set of values.
   * @param values - a series of values
   * @return the standard deviation
   */
  public double std(double... values) {
    double mean = mean(values);

    double sum = 0.0;
    for (double x : values) {
      sum += Math.pow((x - mean), 2);
    }

    return Math.sqrt(sum / (values.length - 1));
  }

  /**
   * Returns the sum of the values.
   * @param values - a series of values
   * @return the sum of the values
   */
  private double getSumOfValues(double... values) {
    double sum = 0;
    for (double v : values) {
      sum += v;
    }
    return sum;
  }

}
