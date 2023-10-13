package dao;

import java.util.List;

public interface MethodDAO<EntityType> {
    
    List<EntityType> selectBySQL(String sql, Object... args);

    List<EntityType> getAll();
    
    EntityType getByID(String id);

    void add(EntityType o);

    void update(EntityType o);

    void remove(String id);
    
}
