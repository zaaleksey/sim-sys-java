package simsys.component;

import java.util.Collection;
import java.util.function.Supplier;
import simsys.core.context.SimulationContext;
import simsys.core.environment.Entity;
import simsys.core.event.Event;
import simsys.core.event.HandledEventBuilderFactory;
import simsys.entity.demand.Demand;
import simsys.node.AbstractNode;
import simsys.node.Node;
import simsys.random.RandomVariable;

public class Source extends AbstractNode {

  @Override
  public Collection<Entity> getEntities() {
    return null;
  }

  @Override
  public Collection<Event> getEvents() {
    return null;
  }

  @Override
  public void receive(Demand d) {
    throw new UnsupportedOperationException(
        "This operations is not supported for source node, : " + this.getClass());
  }


  public static class SourceBuilder {

    private HandledEventBuilderFactory eventBuilderFactory;
    private RandomVariable interArrivalTimes;
    private Supplier<Demand> demandSupplier;
    private RouteFunction routeFunction;
    private SimulationContext simulationContext;

    public SourceBuilder(SimulationContext simulationContext,
        HandledEventBuilderFactory eventBuilderFactory) {
      this.simulationContext = simulationContext;
      this.eventBuilderFactory = eventBuilderFactory;
    }

    public SourceBuilder interArrivalTimes(RandomVariable randomVariable) {
      this.interArrivalTimes = randomVariable;
      return this;
    }

    public SourceBuilder routeFunction(RouteFunction routeFunction) {
      this.routeFunction = routeFunction;
      return this;
    }

    public SourceBuilder demandSupplier(Supplier<Demand> demandSupplier) {
      this.demandSupplier = demandSupplier;
      return this;
    }

    public Source build() {
      Source source = new Source();

      Event event = eventBuilderFactory
          .create()
          .periodic(interArrivalTimes)
          .addHandler(e -> {
            Demand d = demandSupplier.get();
            d.setCreationTime(simulationContext.getCurrentTime());
            Node node = routeFunction.route();
            node.receive(d);
          })
          .build();
      source.addEvent(event);
      return source;
    }
  }
}
