package service;

import javafx.collections.ObservableList;
import model.dto.Employee;

public interface EmployeeService {
    ObservableList<Employee> getEmployeeDetails();
}
