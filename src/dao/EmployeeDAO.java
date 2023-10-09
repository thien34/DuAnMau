package dao;

import entity.Employee;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.JdbcHelper;

public class EmployeeDAO implements MethodDAO<Employee, String> {

    String GET_ALL = "Select * From Employee";
    String GET_BY_ID = "Select * From Employee Where ID = ?";
    String INSERT = "Insert Employee(ID, Pass, Name, Role) VALUES (?, ?, ?, ?)";
    String UPDATE = "Update Employee Set Pass= ?, Name= ?, Role= ? Where ID = ?";
    String DELETE = "Delete Employee Where ID = ?";

    @Override
    public List<Employee> getAll() {
        return selectBySQL(GET_ALL);
    }

    @Override
    public Employee getByID(String id) {
        List<Employee> list = this.selectBySQL(GET_BY_ID, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public void add(Employee o) {
        JdbcHelper.executeUpdate(INSERT,
                o.getId(),
                o.getPass(),
                o.getName(),
                o.getRole()
        );
    }

    @Override
    public void update(Employee o) {
        JdbcHelper.executeUpdate(UPDATE,
                o.getPass(),
                o.getName(),
                o.getRole(),
                o.getId()
        );
    }

    @Override
    public void remove(String id) {
        JdbcHelper.executeUpdate(DELETE, id);
    }

    @Override
    public List<Employee> selectBySQL(String sql, Object... args) {
        List<Employee> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.executeQuery(sql, args);
            while (rs.next()) {
                Employee employee = Employee.builder()
                        .id(rs.getString(1))
                        .pass(rs.getString(2))
                        .name(rs.getString(3))
                        .role(rs.getBoolean(4))
                        .build();
                list.add(employee);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
