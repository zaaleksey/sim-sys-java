package simsys.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * All methods annotated with this annotation use triggers for other methods.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Trigger {

  /**
   * @return class
   */
  Class<?> clazz();

  /**
   * @return name
   */
  String methodName();

  /**
   * @return args
   */
  Class<?>[] args() default {};
}