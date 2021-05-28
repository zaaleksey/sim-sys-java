package simsys.examples;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import simsys.core.CoreConfig;

/**
 * Check Spring Framework (application context)
 */
public class Demo {

  public static void main(String[] args) {

    ApplicationContext context = new AnnotationConfigApplicationContext(CoreConfig.class);

    System.out.println("\nBeans:");
    for (String beanName : context.getBeanDefinitionNames()) {
      System.out.println(beanName);
    }

  }

}
