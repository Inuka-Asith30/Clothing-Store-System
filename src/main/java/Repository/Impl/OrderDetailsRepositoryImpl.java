package Repository.Impl;

import DB.DBConnection;
import Repository.OrderDetailsRepository;
import model.dto.OrderDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderDetailsRepositoryImpl implements OrderDetailsRepository {

    @Override
    public boolean addOrderDetails(OrderDetails orderDetails) {
        try {
            Connection connection= DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO orderdetail(OrderID,ProductId,OrderQTY,Discount) VALUES(?,?,?,?)");
            preparedStatement.setObject(1,orderDetails.getOrderId());
            preparedStatement.setObject(2,orderDetails.getProductId());
            preparedStatement.setObject(3,orderDetails.getQty());
            preparedStatement.setObject(4,orderDetails.getDiscount());

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
