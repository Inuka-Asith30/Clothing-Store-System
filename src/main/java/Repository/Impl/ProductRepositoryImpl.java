package Repository.Impl;

import DB.DBConnection;
import Repository.ProductRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRepositoryImpl implements ProductRepository {
    @Override
    public ResultSet getAllDetails() {
        Connection connection= null;
        try {
            connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("select * from product");
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
