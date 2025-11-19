package service.Impl;

import Repository.EmployeeRepository;
import Repository.Impl.EmployeeRepositoryImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.dto.Employee;
import service.EmployeeService;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeServiceImpl implements EmployeeService {

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

    @Override
    public boolean addEmployeeDetails(Employee employee) {
        return employeeRepository.addEmpDetails(employee);
    }

    @Override
    public boolean deleteEmployeeDetails(String employeeId) {
        return employeeRepository.deleteEmpDetails(employeeId);
    }

    @Override
    public boolean updateEmloyeeDetails(Employee employee) {
        return employeeRepository.updateEmpDetails(employee);
    }

    @Override
    public Employee searchEmployee(String employeeId) {

        ResultSet resultSet = employeeRepository.searchEmployee(employeeId);

        Employee employee=null;

        try {
            while(resultSet.next()){
                employee=new Employee(
                        resultSet.getString("EmpID"),
                        resultSet.getString("Title"),
                        resultSet.getString("EmpName"),
                        resultSet.getDouble("Salary"),
                        resultSet.getString("EmpAddress"),
                        resultSet.getString("Position"),
                        resultSet.getString("Email"),
                        resultSet.getString("PhoneNumber")
                );
            }

            return employee;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getNewEmployeeID() {
        ResultSet resultSet=employeeRepository.getLastEmpID();

        String lastId = null;

        try {
            while(resultSet.next()){
                lastId=resultSet.getString("EmpID");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(lastId!=null){
            lastId = lastId.split("[A-Z]")[1];//C060-->060
            String newId= String.format("C%03d",(Integer.parseInt(lastId)+1));

            return newId;
        }

        return "C001";


    }


}
