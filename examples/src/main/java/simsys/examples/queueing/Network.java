package simsys.examples.queueing;

import java.util.Collections;
import java.util.Random;
import simsys.core.SimulationComponent;
import simsys.core.clock.Clock;
import simsys.core.clock.ClockImpl;
import simsys.core.context.SimulationContextImpl;
import simsys.core.context.SimulationContext;
import simsys.core.environment.EnvironmentImpl;
import simsys.core.event.HandledEvent;
import simsys.core.exception.ImpossibleEventTime;
import simsys.core.model.SimulationModel;
import simsys.core.model.SimulationModelImpl;
import simsys.core.provider.EventProvider;
import simsys.core.provider.EventProviderImpl;
import simsys.entity.demand.Demand;
import simsys.entity.demand.SimpleDemand;
import simsys.random.ExponentialRV;

public class Network {

  private SimulationModel model;

  private Network installModel(SimulationModel model) {
    this.model = model;
    return this;
  }

  public void run() {
    model.run();
  }

  public static class NetworkBuilder {

    private final EnvironmentImpl env;
    private final Clock clock;
    private final EventProvider eventProvider;
    private final SimulationContext simulationContext;
    private final SimulationModel model;

    public NetworkBuilder() {
      env = new EnvironmentImpl();
      clock = new ClockImpl();
      eventProvider = new EventProviderImpl(Collections.emptyList());
      simulationContext = new SimulationContextImpl(env, clock, eventProvider);
      model = new SimulationModelImpl(simulationContext);
    }

    public NetworkBuilder addComponent(String id, SimulationComponent component) {
      env.addComponent(id, component);
      return this;
    }

    public NetworkBuilder addHandler(HandledEvent handledEvent) {
      try {
        eventProvider.add(handledEvent);
      } catch (ImpossibleEventTime impossibleEventTime) {
        impossibleEventTime.printStackTrace();
      }
      return this;
    }
//
//        public NetworkBuilder addConnection(String out, String in) {
//            for(SimulationComponent component: env.getComponents().values()) {
//                if(component.getId().equals(out)) {
//                    component.getConnectedComponents().add(env.getComponents().get(in));
//                }
//            }
//            return this;
//        }

    public Network build() {
      HandledEvent expPeriodic = new HandledEvent.HandledEventBuilder(simulationContext)
          .periodic(new ExponentialRV(new Random(), 1))
          // TODO: прописать логику создания и отправки из источника
          .addHandler(event -> {
            Demand demand = new SimpleDemand(clock.getCurrentTime());
            //TODO: куда отправлять?
          })
          .build();
      try {
        eventProvider.add(expPeriodic);
      } catch (ImpossibleEventTime impossibleEventTime) {
        impossibleEventTime.printStackTrace();
      }
      return new Network().installModel(model);
    }

  }

}
