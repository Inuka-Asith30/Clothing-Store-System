package service;

import javafx.collections.ObservableList;
import model.dto.Employee;

public interface EmployeeService {
    ObservableList<Employee> getEmployeeDetails();
    boolean addEmployeeDetails(Employee employee);
    boolean deleteEmployeeDetails(String employeeId);
    boolean updateEmloyeeDetails(Employee employee);
}
