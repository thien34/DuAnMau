package service;

import dao.CourseDAO;
import entity.Course;
import java.util.List;

public class CourseService implements MethodService<Course, String> {

    @Override
    public List<Course> selectBySQL(String sql, Object... args) {
        return null;
    }

    @Override
    public List<Course> getAll() {
        return new CourseDAO().getAll();
    }

    @Override
    public Course getByID(String id) {
        return new CourseDAO().getByID(id);
    }

    @Override
    public void add(Course o) {
        new CourseDAO().add(o);
    }

    @Override
    public void update(Course o) {
        new CourseDAO().update(o);
    }

    @Override
    public void remove(String id) {
        new CourseDAO().remove(id);
    }

}
