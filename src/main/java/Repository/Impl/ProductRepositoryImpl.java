package Repository.Impl;

import DB.DBConnection;
import Repository.ProductRepository;
import model.dto.Product;

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

    @Override
    public boolean addDetails(Product product) {
        try {
            Connection connection=DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO Product(SupplierId,ProductId,ProductName,Category,PackSize,unitPrice,qtyOnHand) VALUES(?,?,?,?,?,?,?)");
            preparedStatement.setObject(1,product.getSupplierId());
            preparedStatement.setObject(2,product.getProductId());
            preparedStatement.setObject(3,product.getProductName());
            preparedStatement.setObject(4,product.getCategory());
            preparedStatement.setObject(5,product.getPackSize());
            preparedStatement.setObject(6,product.getUnitPrice());
            preparedStatement.setObject(7,product.getQtyOnHand());

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteDetails(String productId) {
        try {
            Connection connection=DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("delete from product where ProductId=?");
            preparedStatement.setObject(1,productId);

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateDetails(Product product) {
        try {
            Connection connection=DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE product SET SupplierId=?,ProductName=?,Category=?,PackSize=?,UnitPrice=?,QtyOnHand=? WHERE ProductId=?");

            preparedStatement.setObject(1,product.getSupplierId());
            preparedStatement.setObject(2,product.getProductName());
            preparedStatement.setObject(3,product.getCategory());
            preparedStatement.setObject(4,product.getPackSize());

            preparedStatement.setObject(5,product.getUnitPrice());
            preparedStatement.setObject(6,product.getQtyOnHand());
            preparedStatement.setObject(7,product.getProductId());

            return preparedStatement.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
