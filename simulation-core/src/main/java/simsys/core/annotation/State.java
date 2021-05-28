package simsys.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * All fields annotated with this annotation are defined as the state of the Agent.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface State {

  /**
   * Defines the initial state. By default, the state is not initial.
   *
   * @return {@code true} if the state is initial, otherwise {@code false}
   */
  boolean initial() default false;

  /**
   * Determines whether statistics should be collected for the state.
   *
   * @return {@code true} if statistics are collected, otherwise {@code false}
   */
  boolean statistic() default true;

}
