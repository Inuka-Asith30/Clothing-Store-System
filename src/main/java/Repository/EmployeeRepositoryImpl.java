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
}
