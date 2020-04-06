package simsys.core.environment;

import simsys.entity.Entity;

public interface Environment<T extends Entity > {
    void addEntity(T entity, String id);

    T getEntity(String id);
}
