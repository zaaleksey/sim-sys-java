package simsys.component.agents;

import java.util.List;

public interface Sender {

  void setReceivers(List<Receiver> receivers);

  void send();

}
