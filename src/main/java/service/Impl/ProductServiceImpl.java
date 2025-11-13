package service.Impl;

import Repository.Impl.ProductRepositoryImpl;
import Repository.ProductRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.dto.Product;
import model.dto.Supplier;
import service.ProductService;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductServiceImpl implements ProductService {

    ProductRepository productRepository=new ProductRepositoryImpl();
    ObservableList<Product> productObservableList= FXCollections.observableArrayList();

    @Override
    public ObservableList<Product> getAllDetails() {
        ResultSet resultSet = productRepository.getAllDetails();

        try {
            while(resultSet.next()){
                productObservableList.add(
                        new Product(
                                resultSet.getString("SupplierId"),
                                resultSet.getString("ProductId"),
                                resultSet.getString("ProductName"),
                                resultSet.getString("Category"),
                                resultSet.getString("PackSize"),
                                resultSet.getDouble("UnitPrice"),
                                resultSet.getInt("QtyOnHand")
                        )
                );
            }

            return productObservableList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean addDetails(Product product) {
        return productRepository.addDetails(product);
    }

    @Override
    public boolean deleteDetails(String productId) {
        return productRepository.deleteDetails(productId);
    }
}
