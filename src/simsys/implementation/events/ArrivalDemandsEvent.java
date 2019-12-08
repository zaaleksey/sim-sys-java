package simsys.implementation.events;

import simsys.api.events.Event;
import simsys.implementation.environment.Environment;
import simsys.mm1.InfoMM1;
import simsys.mm1.Demand;
import simsys.mm1.Queue;

import java.util.logging.Logger;

public class ArrivalDemandsEvent extends Event {

    private Logger logger = Logger.getLogger(ArrivalDemandsEvent.class.getName());

    private Environment environment;
    private Queue queue;
    private InfoMM1 infoMM1;

    public ArrivalDemandsEvent(
            double actionEventTime, Environment environment, Queue queue,
            InfoMM1 info) {
        this.actionEventTime = actionEventTime;
        this.environment = environment;
        this.queue = queue;
        this.infoMM1 = info;
    }

    @Override
    public void actionEvent() {

        Demand demand = new Demand(actionEventTime);
        logger.info("Demand ID: " + demand.ID + " arrival. Time: " + actionEventTime);

        if (queue.isEmpty()) {
            // планируем событие обслуживания на тоже время что и приход требования
            servicePlanning(actionEventTime, demand);
        } else {
            // планируем событие обслуживания на время окончания обслуживания последнего
            // требования в очереде требований
            double time = queue.getLast().getServiceCompletionTime();
            servicePlanning(time, demand);
        }
        queue.push(demand);
    }

    private void servicePlanning(double actionTime, Demand demand) {
        // устанавливаем начало обслуживания требования
        demand.setServiceStartTime(actionTime);
        // расчитываем окончание обслуживания требования
        demand.setServiceCompletionTime(
                demand.getServiceStartTime() + infoMM1.getNextCareVariable());
        // создаем и заносим событие в упорядоченный контейнер
        Event serviceEvent = new ServicedDemandEvent(
                demand.getServiceStartTime(), environment, queue, infoMM1);
        environment.getEventContainer().addEvent(serviceEvent);
    }
}
