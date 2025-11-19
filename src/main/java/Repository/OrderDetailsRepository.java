package Repository;

import model.dto.OrderDetails;

public interface OrderDetailsRepository {
    boolean addOrderDetails(OrderDetails orderDetails);
}
