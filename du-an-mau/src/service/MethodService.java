package service;

import java.util.List;

public interface MethodService<EntityType> {

    List<EntityType> selectBySQL(String sql, Object... args);

    List<EntityType> getAll();

    EntityType getByID(String id);

    void add(EntityType o);

    void update(EntityType o);

    void remove(String id);

}
