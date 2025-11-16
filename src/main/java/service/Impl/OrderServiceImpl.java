package service.Impl;

import Repository.Impl.OrderRepositoryImpl;
import Repository.OrderRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.dto.Product;
import service.OrderService;
import service.ProductService;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderServiceImpl implements OrderService {

    OrderRepository orderRepository=new OrderRepositoryImpl();
    ProductService productService=new ProductServiceImpl();

    @Override
    public ObservableList<String> getProductsId(String category) {
        ResultSet resultSet = orderRepository.getProductsId(category);

        ObservableList<String> allProductsId= FXCollections.observableArrayList();

        try {
            while(resultSet.next()){
                allProductsId.add(resultSet.getString("ProductId"));
            }

            return allProductsId;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Product searchProduct(String productId) {
        return productService.searchProduct(productId);
    }
}
