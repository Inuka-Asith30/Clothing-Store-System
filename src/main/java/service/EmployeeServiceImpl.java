package service;

import Repository.EmployeeRepository;
import Repository.EmployeeRepositoryImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.dto.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeServiceImpl implements EmployeeService{

    EmployeeRepository employeeRepository=new EmployeeRepositoryImpl();
    @Override
    public ObservableList<Employee> getEmployeeDetails() {


        ObservableList<Employee> employees= FXCollections.observableArrayList();

        ResultSet empDetails = employeeRepository.getEmpDetails();


        try {
            while(empDetails.next()){
                employees.add(
                        new Employee(
                                empDetails.getString("EmpID"),
                                empDetails.getString("Title"),
                                empDetails.getString("EmpName"),
                                empDetails.getDouble("Salary"),
                                empDetails.getString("EmpAddress"),
                                empDetails.getString("PostalCode"),
                                empDetails.getString("Position"),
                                empDetails.getString("Email"),
                                empDetails.getString("PhoneNumber")
                        )
                );
            }

            return employees;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
