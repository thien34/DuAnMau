package service;

import dao.ThematicDAO;
import entity.Thematic;
import java.util.List;

public class ThematicService implements MethodService<Thematic, String> {

    @Override
    public List<Thematic> getAll() {
        return new ThematicDAO().getAll();
    }

    @Override
    public Thematic getByID(String id) {
        return new ThematicDAO().getByID(id);
    }

    @Override
    public void add(Thematic object) {
        new ThematicDAO().add(object);
    }

    @Override
    public void update(Thematic object) {
        new ThematicDAO().update(object);
    }

    @Override
    public void remove(String id) {
        new ThematicDAO().remove(id);
    }

    public Thematic getByName(String name) {
        return new ThematicDAO().getByName(name);
    }
    @Override
    public List<Thematic> selectBySQL(String sql, Object... args) {
        return null;
    }

}
