package Repository.Impl;

import DB.DBConnection;
import Repository.EmployeeRepository;
import model.dto.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRepositoryImpl implements EmployeeRepository {
    @Override
    public ResultSet getEmpDetails() {

        try {

            Connection connection=DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("select * from employee");
            ResultSet resultSet=preparedStatement.executeQuery();

            return resultSet;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean addEmpDetails(Employee employee) {
        try {
            Connection connection=DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO employee(EmpID,Title,EmpName,Salary,EmpAddress,Position,Email,PhoneNumber) VALUES(?,?,?,?,?,?,?,?)");
            preparedStatement.setObject(1,employee.getEmployeeId());
            preparedStatement.setObject(2,employee.getTitle());
            preparedStatement.setObject(3,employee.getName());
            preparedStatement.setObject(4,employee.getSalary());
            preparedStatement.setObject(5,employee.getAddress());
            preparedStatement.setObject(6,employee.getPosition());
            preparedStatement.setObject(7,employee.getEmail());
            preparedStatement.setObject(8,employee.getPhoneNumber());

            return preparedStatement.executeUpdate() > 0;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteEmpDetails(String employeeId) {
        try {
            Connection connection=DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("delete from employee where EmpID=?");
            preparedStatement.setObject(1,employeeId);

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateEmpDetails(Employee employee) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("UPDATE employee SET Title=?,EmpName=?,Salary=?,EmpAddress=?,Position=?,Email=?,PhoneNumber=? WHERE EmpID=?");
            preparedStatement.setObject(1,employee.getTitle());
            preparedStatement.setObject(2,employee.getName());
            preparedStatement.setObject(3,employee.getSalary());
            preparedStatement.setObject(4,employee.getAddress());
            preparedStatement.setObject(5,employee.getPosition());
            preparedStatement.setObject(6,employee.getEmail());
            preparedStatement.setObject(7,employee.getPhoneNumber());
            preparedStatement.setObject(8,employee.getEmployeeId());

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public ResultSet searchEmployeeById(String empId) {

        try {
            Connection connection=DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("select * from employee where EmpID=?");
            preparedStatement.setObject(1,empId);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public ResultSet getLastEmpID() {
        try {
            Connection connection=DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("select EmpID from employee Order by EmpID DESC LImit 1");
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResultSet searchEmployeeByEmail(String email) {
        try {
            Connection connection=DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("select * from employee where Email=?");
            preparedStatement.setObject(1,email);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
