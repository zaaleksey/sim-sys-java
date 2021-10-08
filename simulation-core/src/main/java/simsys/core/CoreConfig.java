package simsys.core;

import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import simsys.core.clock.Clock;
import simsys.core.clock.MemoryClock;
import simsys.core.context.SimulationContext;
import simsys.core.context.SimulationContextImpl;
import simsys.core.environment.Environment;
import simsys.core.environment.EnvironmentImpl;
import simsys.core.provider.EventProvider;
import simsys.core.provider.EventProviderImpl;

/**
 *
 */
@Configuration
@ComponentScan()
public class CoreConfig {

  /**
   * @return simulation context
   */
  @Bean
  public SimulationContext simulationContext() {
    return new SimulationContextImpl();
  }

  /**
   * @return new environment
   */
  @Bean
  public Environment environment() {
    return new EnvironmentImpl();
  }

  /**
   * @return new clock
   */
  @Bean
  public Clock clock() {
    return new MemoryClock();
  }

  /**
   * @return eventProvider
   */
  @Bean
  public EventProvider eventProvider() {
    return new EventProviderImpl(Collections.emptyList());
  }

}
