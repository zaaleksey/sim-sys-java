package simsys.component;

import simsys.core.agent.AbstractAgent;


public abstract class AbstractSource extends AbstractAgent {

  private static final String SLEEPING_STATE = "SLEEPING";
  private static final String ERROR_MESSAGE = "Method is not implemented";


  void arrivalAction() {
    throw new UnsupportedOperationException(ERROR_MESSAGE);
  }

  double interArrivalTime() {
    throw new UnsupportedOperationException(ERROR_MESSAGE);
  }


}
