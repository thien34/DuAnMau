package service;

import dao.LearnerDAO;
import entity.Learner;
import java.util.List;

public class LearnerService implements MethodService<Learner, String> {

    @Override
    public List<Learner> selectBySQL(String sql, Object... args) {
        return null;
    }

    @Override
    public List<Learner> getAll() {
        return new LearnerDAO().getAll();
    }

    @Override
    public Learner getByID(String id) {
        return new LearnerDAO().getByID(id);
    }

    @Override
    public void add(Learner o) {
        new LearnerDAO().add(o);
    }

    @Override
    public void update(Learner o) {
        new LearnerDAO().update(o);
    }

    @Override
    public void remove(String id) {
        new LearnerDAO().remove(id);
    }
    
    public List<Learner> search(String n) {
        return new LearnerDAO().search(n);
    } 

}
