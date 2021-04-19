package simsys.transfer;

import java.util.List;

public interface Sender {

  void setReceivers(List<Receiver> receivers);

  void send();

//  implementation
//  @Override
//  public void send() {
//    receivers.forEach(Receiver::receive);
//  }

}
