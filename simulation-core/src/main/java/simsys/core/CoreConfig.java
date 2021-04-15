package simsys.core;

import java.util.Collections;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import simsys.core.clock.Clock;
import simsys.core.clock.ClockImpl;
import simsys.core.context.SimulationContext;
import simsys.core.context.SimulationContextImpl;
import simsys.core.environment.Environment;
import simsys.core.environment.EnvironmentImpl;
import simsys.core.provider.EventProvider;
import simsys.core.provider.EventProviderImpl;

@Configuration
@ComponentScan()
public class CoreConfig {

  @Bean
  public SimulationContext simulationContext() {
    return new SimulationContextImpl();
  }

  @Bean
  public Environment environment() {
    return new EnvironmentImpl();
  }

  @Bean
  public Clock clock() {
    return new ClockImpl();
  }

  @Bean
  public EventProvider eventProvider() {
    return new EventProviderImpl(Collections.emptyList());
  }

  public static void main(String[] args) {
    new AnnotationConfigApplicationContext(CoreConfig.class);
  }

}
