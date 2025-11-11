package Repository;

import model.dto.Employee;

import java.sql.ResultSet;

public interface EmployeeRepository{
    ResultSet getEmpDetails();
    boolean addEmpDetails(Employee employee);
}
