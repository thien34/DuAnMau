package service;

import dao.StudentDAO;
import entity.Student;
import java.util.List;

public class StudentService implements MethodService<Student> {

    public List<Student> getByIDCourse(String id) {
        return new StudentDAO().getByIDCourse(id);
    }

    @Override
    public List<Student> getAll() {
        return new StudentDAO().getAll();
    }

    @Override
    public Student getByID(String id) {
        return new StudentDAO().getByID(id);
    }

    @Override
    public void add(Student o) {
        new StudentDAO().add(o);
    }

    @Override
    public void update(Student o) {
        new StudentDAO().update(o);
    }

    @Override
    public void remove(String id) {
        new StudentDAO().remove(id);
    }

    @Override
    public List<Student> selectBySQL(String sql, Object... args) {
        return null;
    }

}
