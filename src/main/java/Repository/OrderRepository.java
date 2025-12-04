package Repository;

import model.dto.Orders;

import java.sql.ResultSet;

public interface OrderRepository {
    ResultSet getProductsId(String category);
    ResultSet getLastOrderId();
    boolean addOrders(Orders orders);
    ResultSet getAllPrepareOrder();
    boolean deleteOrder(String orderId);
    boolean updateOrder(String orderId,String orderStatus);
}
