package Repository.Impl;

import DB.DBConnection;
import Repository.SystemAccessRepository;
import model.dto.SystemAccess;

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
    public boolean addDetails(SystemAccess systemAccess) {
        try {
            Connection connection=DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO systemAccess(empID,Password) VALUES(?,?)");
            preparedStatement.setObject(1,systemAccess.getEmployeeId());
            preparedStatement.setObject(2,systemAccess.getPassword());

            return preparedStatement.executeUpdate()>0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteDetails(String empId) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("delete from systemAccess where EmpID=?");
            preparedStatement.setObject(1,empId);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateDetails(SystemAccess systemAccess) {
        try {
            Connection connection=DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE systemAccess SET Password=? WHERE EmpID=?");

            preparedStatement.setObject(1,systemAccess.getPassword());
            preparedStatement.setObject(2,systemAccess.getEmployeeId());

            return preparedStatement.executeUpdate()>0;
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
