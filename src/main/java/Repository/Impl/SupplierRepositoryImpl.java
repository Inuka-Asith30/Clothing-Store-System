package Repository.Impl;

import DB.DBConnection;
import Repository.SupplierRepository;
import javafx.collections.ObservableList;
import model.dto.Supplier;
import service.SupplierService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplierRepositoryImpl implements SupplierRepository {

    @Override
    public ResultSet getAllDetails() {
        try {
            Connection connection=DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("select * from supplier");
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
