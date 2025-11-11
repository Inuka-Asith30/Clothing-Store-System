package Repository;

import DB.DBConnection;
import javafx.collections.ObservableList;
import model.dto.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRepositoryImpl implements EmployeeRepository{
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

}
