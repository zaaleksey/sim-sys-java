package symsys.statistic;

import com.google.common.math.StatsAccumulator;

import javax.annotation.concurrent.NotThreadSafe;

/**
 * A mutable object which accumulates double values and tracks some basic statistics over all the
 * values added so far. The values may be added singly or in groups. This class is not thread safe.
 */
@NotThreadSafe
public class StatisticAccumulator {

  /**
   * A mutable object which accumulates double values and tracks some basic statistics over all the
   * values added so far.
   */
  private StatsAccumulator accumulator;

  public StatisticAccumulator() {
    accumulator = new StatsAccumulator();
  }

  /**
   * Adds the given value to the dataset.
   *
   * @param value a value
   */
  public void add(double value) {
    accumulator.add(value);
  }

  /**
   * Adds the given values to the dataset.
   *
   * @param values a series of values
   */
  public void addAll(double... values) {
    accumulator.addAll(values);
  }

  /**
   * Adds the given values to the dataset.
   *
   * @param values a series of values
   */
  public void addAll(int... values) {
    accumulator.addAll(values);
  }

  /**
   * Adds the given values to the dataset.
   *
   * @param values a series of values
   */
  public void addAll(long... values) {
    accumulator.addAll(values);
  }

  /**
   * Returns the number of values.
   *
   * @return the number of values
   */
  public long count() {
    return accumulator.count();
  }

  /**
   * Returns the arithmetic mean of the values. The count must be non-zero.
   * If these values are a sample drawn from a population, this is also an unbiased estimator of the arithmetic mean of the population.
   *
   * @throws IllegalStateException - if the dataset is empty
   * @return the arithmetic mean of the values
   */
  public double mean() {
    return accumulator.mean();
  }

  /**
   * Returns the sum of the values.
   *
   * @return the sum of the values
   */
  public final double sum() {
    return accumulator.sum();
  }

  /**
   * Returns the population variance of the values. The count must be non-zero.
   * This is guaranteed to return zero if the dataset contains only exactly one finite value.
   * It is not guaranteed to return zero when the dataset consists of the same value multiple times,
   * due to numerical errors. However, it is guaranteed never to return a negative result.
   *
   * @throws IllegalStateException - if the dataset is empty
   * @return the population variance of the values
   */
  public final double populationVariance() {
    return accumulator.populationVariance();
  }

  /**
   * Returns the population standard deviation of the values. The count must be non-zero.
   * This is guaranteed to return zero if the dataset contains only exactly one finite value.
   * It is not guaranteed to return zero when the dataset consists of the same value multiple times,
   * due to numerical errors. However, it is guaranteed never to return a negative result.
   *
   * @throws IllegalStateException - if the dataset is empty
   * @return the population standard deviation of the values
   */
  public final double populationStandardDeviation() {
    return accumulator.populationStandardDeviation();
  }

  /**
   * Returns the unbiased sample variance of the values. If this dataset is a sample drawn from a population,
   * this is an unbiased estimator of the population variance of the population. The count must be greater than one.
   * This is not guaranteed to return zero when the dataset consists of the same value multiple times,
   * due to numerical errors. However, it is guaranteed never to return a negative result.
   *
   * @throws IllegalStateException - if the dataset is empty or contains a single value
   * @return the unbiased sample variance of the values
   */
  public final double sampleVariance() {
    return accumulator.sampleVariance();
  }

  /**
   * Returns the corrected sample standard deviation of the values. If this dataset is a sample drawn from a population,
   * this is an estimator of the population standard deviation of the population which is less biased than populationStandardDeviation()
   * (the unbiased estimator depends on the distribution). The count must be greater than one.
   * This is not guaranteed to return zero when the dataset consists of the same value multiple times,
   * due to numerical errors. However, it is guaranteed never to return a negative result.
   *
   * @throws IllegalStateException - if the dataset is empty or contains a single value
   * @return the corrected sample standard deviation of the values
   */
  public final double sampleStandardDeviation() {
    return accumulator.sampleStandardDeviation();
  }

  /**
   * Returns the lowest value in the dataset. The count must be non-zero.
   *
   * @throws IllegalStateException - if the dataset is empty
   * @return the lowest value in the dataset
   */
  public double min() {
    return accumulator.min();
  }

  /**
   * Returns the highest value in the dataset. The count must be non-zero.
   *
   * @throws IllegalStateException - if the dataset is empty
   * @return the highest value in the dataset
   */
  public double max() {
    return accumulator.max();
  }

}
