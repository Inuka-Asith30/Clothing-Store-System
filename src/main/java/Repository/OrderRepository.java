package Repository;

import java.sql.ResultSet;

public interface OrderRepository {
    ResultSet getProductsId(String category);
}
