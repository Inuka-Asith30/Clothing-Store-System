package service;

import javafx.collections.ObservableList;
import model.dto.Product;

public interface ProductService {
    ObservableList<Product> getAllDetails();
    boolean addDetails(Product product);
    boolean deleteDetails(String productId);
    boolean updateDetails(Product product);
    Product searchProduct(String productId);
}
