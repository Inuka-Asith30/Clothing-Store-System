package Repository.Impl;

import DB.DBConnection;
import Repository.SystemAccessRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SystemAccessRepositoryImpl implements SystemAccessRepository {
    @Override
    public ResultSet getAllDetails() {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("select * from systemAccess");
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResultSet searchEmployee(String empId) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("select * from systemAccess where EmpID=?");
            preparedStatement.setObject(1,empId);

            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
