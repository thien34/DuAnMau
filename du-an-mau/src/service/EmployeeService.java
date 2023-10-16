package service;

import dao.EmployeeDAO;
import entity.Employee;
import java.util.List;

public class EmployeeService implements MethodService<Employee> {

    @Override
    public List<Employee> selectBySQL(String sql, Object... args) {
        return null;
    }

    @Override
    public List<Employee> getAll() {
        return new EmployeeDAO().getAll();
    }

    @Override
    public Employee getByID(String id) {
        return new EmployeeDAO().getByID(id);
    }

    @Override
    public void add(Employee o) {
        new EmployeeDAO().add(o);
    }

    @Override
    public void update(Employee o) {
        new EmployeeDAO().update(o);
    }

    @Override
    public void remove(String id) {
        new EmployeeDAO().remove(id);
    }

}
