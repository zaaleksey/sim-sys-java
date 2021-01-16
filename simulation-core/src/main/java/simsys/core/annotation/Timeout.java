package simsys.core.annotation;

public @interface Timeout {

  double value() default 0;

  String timeoutFunction() default "'";
}
