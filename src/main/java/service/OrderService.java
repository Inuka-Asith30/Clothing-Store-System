package service;

import javafx.collections.ObservableList;
import model.dto.Product;

public interface OrderService {
    ObservableList<String> getProductsId(String category);
    Product searchProduct(String productId);
}
