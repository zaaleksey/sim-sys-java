package simsys.mm1;

import simsys.api.random.RandomVariable;

public class Service {

    private boolean status = false;
    private RandomVariable randomVariable;

    public Service(RandomVariable randomVariable) {
        this.randomVariable = randomVariable;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public RandomVariable getRandomVariable() {
        return randomVariable;
    }

    public void setRandomVariable(RandomVariable randomVariable) {
        this.randomVariable = randomVariable;
    }
}
