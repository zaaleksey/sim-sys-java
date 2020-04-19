package simsys.entity.system;

import simsys.entity.demand.Demand;
import simsys.random.RandomVariable;

// это прибор?  или система целиком
public interface System {
    RandomVariable getRandomVariable();

    void setRandomVariable(RandomVariable randomVariable);

    void service(Demand demand, double currentTime);
}
