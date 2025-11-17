package service.Impl;

import Repository.Impl.OrderDetailsRepositoryImpl;
import Repository.Impl.OrderRepositoryImpl;
import Repository.OrderDetailsRepository;
import Repository.OrderRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.dto.AddToCard;
import model.dto.OrderDetails;
import model.dto.Orders;
import model.dto.Product;
import service.OrderService;
import service.ProductService;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderServiceImpl implements OrderService {

    OrderRepository orderRepository=new OrderRepositoryImpl();
    ProductService productService=new ProductServiceImpl();
    OrderDetailsRepository orderDetailsRepository=new OrderDetailsRepositoryImpl();

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

    @Override
    public String getNewOrderId() {
        ResultSet resultSet = orderRepository.getLastOrderId();

        String lastOrderId=null;

        try {
            while(resultSet.next()){
                lastOrderId=resultSet.getString("OrderID");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(lastOrderId!=null){
            lastOrderId=lastOrderId.split("[A-Z]")[1]; //D001-->001
            String newOrderId=String.format("D%03d",Integer.parseInt(lastOrderId)+1);

            return newOrderId;
        }
        return "D001";

    }

    @Override
    public boolean addOrder(Orders orders, ObservableList<AddToCard> addToCardObservableList) {
        boolean isAddedOrders=orderRepository.addOrders(orders);

        if((isAddedOrders && addOrderDetails(orders,addToCardObservableList))==true){
            return true;
        }
        return false;
    }

    private boolean addOrderDetails(Orders orders,ObservableList<AddToCard> addToCardObservableList) {

        boolean isAdded=true;

        for(AddToCard row:addToCardObservableList){
            isAdded=orderDetailsRepository.addOrderDetails(new OrderDetails(orders.getOrderId(), row.getProductId(), row.getQty(), row.getDiscount()));

            if(isAdded!=true){
                return isAdded;
            }
        }
        return isAdded;
    }
}
