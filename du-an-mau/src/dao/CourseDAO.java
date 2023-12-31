package dao;

import entity.Course;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.JdbcHelper;

public class CourseDAO implements MethodDAO<Course> {

    String GET_ALL = "Select * From Course";
    String GET_BY_ID = "Select * From Course Where ID = ?";
    String INSERT = "Insert Course(IDThematic, IDEmployee, Tuition, Time, OpeningDay, Note, DateCreated) VALUES (?, ?, ?, ?, ?, ?, ?)";
    String UPDATE = "Update Course Set IDThematic = ?, OpeningDay = ?, Note = ? Where ID = ?";
    String DELETE = "Delete Course Where ID = ?";

    @Override
    public List<Course> getAll() {
        return selectBySQL(GET_ALL);
    }

    public Course getIO(String name, String day) {
        String GET_BY_IDT_OD = "Select * From Course Where IDThematic = ? AND OpeningDay = ?";
        List<Course> list = this.selectBySQL(GET_BY_IDT_OD, name, day);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public List<Course> getDemo(String name) {
        String GET_Demo = "Select * From Course Where IDThematic = ?";
        return selectBySQL(GET_Demo, name);
    }

    public List<Integer> getYears() {
        String SQL = "SELECT DISTINCT YEAR(OpeningDay) Year FROM Course ORDER BY Year DESC";
        List<Integer> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.executeQuery(SQL);
            while (rs.next()) {
                list.add(rs.getInt(1));
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Course getByID(String id) {
        List<Course> list = this.selectBySQL(GET_BY_ID, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public void add(Course o
    ) {
        JdbcHelper.executeUpdate(INSERT,
                o.getIdThematic(),
                o.getIdEmployee(),
                o.getTuition(),
                o.getTime(),
                o.getOpeningDay(),
                o.getNote(),
                o.getDateCreated()
        );
    }

    @Override
    public void update(Course o
    ) {
        JdbcHelper.executeUpdate(UPDATE,
                o.getIdThematic(),
                o.getOpeningDay(),
                o.getNote(),
                o.getId()
        );
    }

    @Override
    public void remove(String id
    ) {
        JdbcHelper.executeUpdate(DELETE, id);
    }

    @Override
    public List<Course> selectBySQL(String sql, Object... args
    ) {
        List<Course> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.executeQuery(sql, args);
            while (rs.next()) {
                Course course = Course.builder()
                        .id(rs.getString(1))
                        .idThematic(rs.getString(2))
                        .idEmployee(rs.getString(3))
                        .tuition(rs.getDouble(4))
                        .time(rs.getDouble(5))
                        .openingDay(rs.getString(6))
                        .note(rs.getString(7))
                        .dateCreated(rs.getString(8))
                        .build();
                list.add(course);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
