package simsys.core.component;

import simsys.core.environment.Entity;
import simsys.core.event.Event;

import java.util.Collection;

public interface Component {
    Collection<Entity> getEntities();

    Collection<Event> getEvents();

    void addEvent(Event event);

    void addEntity(Entity entity);
}
