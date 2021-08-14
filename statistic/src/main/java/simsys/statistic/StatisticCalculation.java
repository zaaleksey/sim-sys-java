package simsys.statistic;

import java.util.Arrays;

/**
 * This object calculates some basic statistics for the entire set of values.
 */
public class StatisticCalculation {

  private StatisticCalculation() {

  }

  /**
   * Returns the standard deviation. Measure of the amount of variation or dispersion of a set of
   * values.
   *
   * @param values a series of values
   * @return the standard deviation
   */
  public static double std(double... values) {
    double mean = mean(values);
    double sum = Arrays.stream(values).map(v -> Math.pow((v - mean), 2)).sum();
    return Math.sqrt(sum / (values.length - 1));
  }

  /**
   * Returns the arithmetic mean of the values. The count must be non-zero.
   *
   * @param values a series of values
   * @return the arithmetic mean of the values
   */
  public static double mean(double... values) {
    return getSumOfValues(values) / values.length;
  }

  /**
   * Returns the sum of the values.
   *
   * @param values a series of values
   * @return the sum of the values
   */
  private static double getSumOfValues(double... values) {
    return Arrays.stream(values).sum();
  }

}
