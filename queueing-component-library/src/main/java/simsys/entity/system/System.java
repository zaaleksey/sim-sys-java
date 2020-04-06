package simsys.entity.system;

import simsys.entity.Entity;
import simsys.entity.demand.Demand;
import simsys.random.RandomVariable;

public interface System extends Entity {
    RandomVariable getRandomVariable();
    void setRandomVariable(RandomVariable randomVariable);
    void service(Demand demand, double currentTime);
}
