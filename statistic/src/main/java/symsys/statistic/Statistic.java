package symsys.statistic;



public class Statistic {

  private Statistic() { }

  public double average(double... values) {
    return getSumOfValues(values) / values.length;
  }

  public double std(double... values) {
    return (1 / values.length) * getSumOfValues(values);
  }

  private double getSumOfValues(double... values) {
    double sum = 0;
    for(double v : values) {
      sum += v;
    }
    return sum;
  }

}
