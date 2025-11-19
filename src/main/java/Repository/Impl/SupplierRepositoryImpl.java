package Repository.Impl;

import DB.DBConnection;
import Repository.SupplierRepository;
import javafx.collections.ObservableList;
import model.dto.Employee;
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

    @Override
    public boolean addDetails(Supplier supplier) {
        try {
            Connection connection=DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO supplier(SupplierId,Name,Address,Email,PhoneNumber) VALUES(?,?,?,?,?)");
            preparedStatement.setObject(1,supplier.getSupplierId());
            preparedStatement.setObject(2,supplier.getName());
            preparedStatement.setObject(3,supplier.getAddress());
            preparedStatement.setObject(4,supplier.getEmail());
            preparedStatement.setObject(5,supplier.getPhoneNumber());

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateDetails(Supplier supplier) {
        try {
            Connection connection=DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE supplier SET Name=?,Address=?,Email=?,PhoneNumber=? WHERE SupplierId=?");

            preparedStatement.setObject(1,supplier.getName());
            preparedStatement.setObject(2,supplier.getAddress());
            preparedStatement.setObject(3,supplier.getEmail());
            preparedStatement.setObject(4,supplier.getPhoneNumber());

            preparedStatement.setObject(5,supplier.getSupplierId());

            return preparedStatement.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteDetails(String supplierId) {
        try {
            Connection connection=DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("delete from supplier where SupplierId=?");
            preparedStatement.setObject(1,supplierId);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResultSet getLastId() {
        try {
            Connection connection=DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("select SupplierId from supplier Order by SupplierId DESC LImit 1");
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResultSet searchDetails(String supplierId) {
        try {
            Connection connection=DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("select * from supplier where SupplierId=?");
            preparedStatement.setObject(1,supplierId);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
