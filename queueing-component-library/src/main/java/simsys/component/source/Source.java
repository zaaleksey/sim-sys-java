package simsys.component.source;

import simsys.transfer.Receiver;

public interface Source {

  void sendDemand();

  void setReceiver(Receiver receiver);

}
