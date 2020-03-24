package simsys.core.environment;

public interface Environment {
    void addEntity(Object entity, String id);

    Object getEntity(String id);


}
