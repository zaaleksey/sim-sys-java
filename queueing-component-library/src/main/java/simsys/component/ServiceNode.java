package simsys.component;

import java.util.ArrayList;
import simsys.core.context.SimulationContext;
import simsys.core.event.Event;
import simsys.core.event.HandledEventBuilderFactory;
import simsys.entity.demand.Demand;
import simsys.entity.queue.Queue;
import simsys.node.AbstractNode;

public class ServiceNode extends AbstractNode {

  private Queue queue;
  private HandledEventBuilderFactory eventBuilderFactory;
  private SimulationContext simulationContext;
  private int ServersCount;
  private ArrayList<Demand> demands;


  private boolean isBusy() {
    return demands.size() == ServersCount;
  }

  @Override
  public void receive(Demand d) {
        /*
         create new event which represents arriving a new demand at the queueing system
         хорошо бы сделать аннотацию, которая логику будет превращать в события автоматически
         все, что внутри хендлера = логика

         need represents a process description language
         like this:
         1 start service:  take demand and start process it
         2 delay (processing)
         3 end service
         there are a builder that allows create a SimulationModel with corresponding events by this process description
        */

    Event startServiceEvent = eventBuilderFactory.create()
        .addHandler(
            e -> {
              if (isBusy()) {
                queue.add(d);
              } else {
                d.setServiceStartTime(simulationContext.getCurrentTime());
                demands.add(d);
                // Запланировать окончание обслуживания
                double serviceTime = 10;
              }
            }
        )
        .startTime(simulationContext.getCurrentTime())
        .build();
  }
}
