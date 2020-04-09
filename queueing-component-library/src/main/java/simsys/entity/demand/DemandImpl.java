package simsys.entity.demand;

public class DemandImpl extends Demand {

    public DemandImpl(double arrivalTime) {
        //конструктор без параметров вызывается автоматически, не имеет смысла писать в явном виде
        super();
        //в явном виде нужно писать только конструктор с параметрами - его пишем действильно первым
        this.arrivalTime = arrivalTime;
    }
}
