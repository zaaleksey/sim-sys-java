package simsys.component.agents;

@FunctionalInterface
public interface Sender {

  void send(Receiver receiver);

}
