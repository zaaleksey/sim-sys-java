package simsys.core.component;

import java.util.ArrayList;
import java.util.Collection;
import simsys.core.environment.Entity;
import simsys.core.event.Event;

public class AbstractComponent implements Component {

  private ArrayList<Entity> entities;
  private ArrayList<Event> events;

  @Override
  public Collection<Entity> getEntities() {
    return entities;
  }

  @Override
  public Collection<Event> getEvents() {
    return events;
  }

  @Override
  public void addEvent(Event event) {
    events.add(event);
  }

  @Override
  public void addEntity(Entity entity) {
    entities.add(entity);
  }

}
