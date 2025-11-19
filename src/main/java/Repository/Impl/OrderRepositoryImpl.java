package Repository.Impl;

import DB.DBConnection;
import Repository.OrderRepository;
import model.dto.Orders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRepositoryImpl implements OrderRepository {
    @Override
    public ResultSet getProductsId(String category) {
        try {
            Connection connection=DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select ProductId from product where Category=?");
            preparedStatement.setObject(1,category);
            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResultSet getLastOrderId() {
        try {
            Connection connection=DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select OrderID from Orders Order by OrderID DESC LImit 1");
            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean addOrders(Orders orders) {
        try {
            Connection connection=DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO Orders(OrderID,OrderDate,OrderStatus) VALUES(?,?,?)");
            preparedStatement.setObject(1,orders.getOrderId());
            preparedStatement.setObject(2,orders.getOrderDate());
            preparedStatement.setObject(3,orders.getOrderStatus());

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
