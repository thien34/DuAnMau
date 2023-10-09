package dao;

import entity.Learner;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.JdbcHelper;

public class LearnerDAO implements MethodDAO<Learner, String> {

    String GET_ALL = "Select ID, IDEmployee, Name, Gender, Birth, Phone, Email, Pass, Note, Registration From Learner";
    String GET_BY_ID = "Select * From Employee Where ID = ?";
    String INSERT = "INSERT Learner (ID, IDEmployee, Name, Gender, Birth, Phone, Email, Pass, Note, Registration) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    String UPDATE = "Update Learner Set IDEmployee = ?, Name = ?, Gender = ?, Birth = ?, Phone = ?, Email = ?, Pass = ?, Note = ?, Registration = ? Where ID = ?";
    String DELETE = "Delete Learner Where ID = ?";
    String SEARCH = "SELECT * FROM Learner WHERE Name LIKE ? Or ID like ? ";

    @Override
    public List<Learner> selectBySQL(String sql, Object... args) {
        List<Learner> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.executeQuery(sql, args);
            while (rs.next()) {
                Learner learner = Learner.builder()
                        .id(rs.getString(1))
                        .idEmployee(rs.getString(2))
                        .name(rs.getString(3))
                        .gender(rs.getBoolean(4))
                        .birth(rs.getString(5))
                        .phone(rs.getString(6))
                        .email(rs.getString(7))
                        .pass(rs.getString(8))
                        .note(rs.getString(9))
                        .registration(rs.getString(10))
                        .build();
                list.add(learner);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Learner> getAll() {
        return selectBySQL(GET_ALL);
    }

    @Override
    public Learner getByID(String id) {
        List<Learner> list = this.selectBySQL(GET_BY_ID, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public void add(Learner o) {
        JdbcHelper.executeUpdate(INSERT,
                o.getId(),
                o.getIdEmployee(),
                o.getName(),
                o.getGender(),
                o.getBirth(),
                o.getPhone(),
                o.getEmail(),
                o.getPass(),
                o.getNote(),
                o.getRegistration()
        );
    }

    @Override
    public void update(Learner o) {
        JdbcHelper.executeUpdate(UPDATE,
                o.getIdEmployee(),
                o.getName(),
                o.getGender(),
                o.getBirth(),
                o.getPhone(),
                o.getEmail(),
                o.getPass(),
                o.getNote(),
                o.getRegistration(),
                o.getId()
        );
    }

    @Override
    public void remove(String id) {
        JdbcHelper.executeUpdate(DELETE, id);
    }

    public List<Learner> search(String n) {
        return selectBySQL(SEARCH, "%" + n + "%", "%" + n + "%");
    }
}
