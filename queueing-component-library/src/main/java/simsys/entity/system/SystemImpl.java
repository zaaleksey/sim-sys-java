package simsys.entity.system;

import simsys.core.model.AbstractSimulationModel;
import simsys.entity.demand.Demand;
import simsys.random.RandomVariable;

public class SystemImpl extends AbstractSimulationModel implements System {

    private RandomVariable randomVariable;

    public SystemImpl(RandomVariable randomVariable) {
        this.randomVariable = randomVariable;
    }

    @Override
    public RandomVariable getRandomVariable() {
        return randomVariable;
    }

    @Override
    public void setRandomVariable(RandomVariable randomVariable) {
        this.randomVariable = randomVariable;
    }

    @Override
    public void service(Demand demand, double currentTime) {
        demand.setServiceStartTime(currentTime);
        demand.setLeavingTime(currentTime + randomVariable.nextValue());
    }


}
