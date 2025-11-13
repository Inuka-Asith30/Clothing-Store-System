package Repository;

import model.dto.Product;

import java.sql.ResultSet;

public interface ProductRepository {
    ResultSet getAllDetails();
    boolean addDetails(Product product);
}
