package symsys.statistic;

public class StatisticCalculation {

  private StatisticCalculation() {
  }

  public double average(double... values) {
    return getSumOfValues(values) / values.length;
  }

  // TODO: не работает
  public double std(double... values) {
    return (1 / values.length) * getSumOfValues(values);
  }

  private double getSumOfValues(double... values) {
    double sum = 0;
    for (double v : values) {
      sum += v;
    }
    return sum;
  }

}
