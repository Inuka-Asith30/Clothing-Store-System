package service;

import javafx.collections.ObservableList;
import model.dto.AddToCard;
import model.dto.Orders;
import model.dto.Product;

public interface OrderService {
    ObservableList<String> getProductsId(String category);
    Product searchProduct(String productId);
    String getNewOrderId();
    boolean addOrder(Orders orders, ObservableList<AddToCard> addToCardObservableList);
    boolean deleteOrder(String orderId);
    boolean updateOrder(String orderId,String orderStatus);

    ObservableList<Orders> getPrepareOrder();
}
