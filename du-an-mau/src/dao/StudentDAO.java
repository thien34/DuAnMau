package dao;

import entity.Student;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.JdbcHelper;

public class StudentDAO implements MethodDAO<Student> {

    String GET_ALL = "Select * From Student";
    String GET_BY_ID = "Select * From Student Where ID = ?";
    String INSERT = "Insert Student(IDCourse, IDLearner,Point) VALUES (?,?,?)";
    String UPDATE = "Update Student Set IDCourse = ?, IDLearner = ?, Point = ? Where ID = ?";
    String DELETE = "Delete Student Where ID = ?";
    String GET_IDCourse = "Select * From Student Where IDCourse = ?";

    public List<Student> getByIDCourse(String id) {
        return selectBySQL(GET_IDCourse, id);
    }

    @Override
    public List<Student> getAll() {
        return selectBySQL(GET_ALL);
    }

    @Override
    public Student getByID(String id) {
        List<Student> list = this.selectBySQL(GET_BY_ID, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public void add(Student o) {
        JdbcHelper.executeUpdate(INSERT,
                o.getIdCourse(),
                o.getIdLearner(),
                o.getPoint()
        );
    }

    @Override
    public void update(Student o) {
        JdbcHelper.executeUpdate(UPDATE,
                o.getIdCourse(),
                o.getIdLearner(),
                o.getPoint(),
                o.getId()
        );
    }

    @Override
    public void remove(String id) {
        JdbcHelper.executeUpdate(DELETE, id);
    }

    @Override
    public List<Student> selectBySQL(String sql, Object... args) {
        List<Student> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.executeQuery(sql, args);
            while (rs.next()) {
                Student student = Student.builder()
                        .id(rs.getInt(1))
                        .idCourse(rs.getString(2))
                        .idLearner(rs.getString(3))
                        .point(rs.getDouble(4))
                        .build();
                list.add(student);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
