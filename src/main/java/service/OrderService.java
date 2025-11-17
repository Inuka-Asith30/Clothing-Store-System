package service;

import javafx.collections.ObservableList;
import model.dto.AddToCard;
import model.dto.OrderDetails;
import model.dto.Orders;
import model.dto.Product;
import org.hibernate.query.Order;

public interface OrderService {
    ObservableList<String> getProductsId(String category);
    Product searchProduct(String productId);
    String getNewOrderId();
    boolean addOrder(Orders orders, ObservableList<AddToCard> addToCardObservableList);
}
