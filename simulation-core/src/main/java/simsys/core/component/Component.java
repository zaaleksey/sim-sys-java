package simsys.core.component;

import java.util.Collection;
import simsys.core.environment.Entity;
import simsys.core.event.Event;

public interface Component {

  Collection<Entity> getEntities();

  Collection<Event> getEvents();

  void addEvent(Event event);

  void addEntity(Entity entity);
}
