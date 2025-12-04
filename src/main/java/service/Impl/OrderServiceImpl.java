package service.Impl;

import Repository.Impl.OrderDetailsRepositoryImpl;
import Repository.Impl.OrderRepositoryImpl;
import Repository.OrderDetailsRepository;
import Repository.OrderRepository;
import controller.InvoiceController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.InvoiceMainDetails;
import model.dto.*;
import model.entity.AddToCardEntity;
import service.OrderService;
import service.ProductService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    List<AddToCardEntity> addToCardEntityList=new ArrayList<>();

    InvoiceController invoiceController=new InvoiceController();

    ObservableList<Orders> Orders = FXCollections.observableArrayList();

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

    @Override
    public boolean deleteOrder(String orderId) {
        return orderRepository.deleteOrder(orderId);
    }

    @Override
    public boolean updateOrder(String orderId, String orderStatus) {
        return orderRepository.updateOrder(orderId,orderStatus);
    }

    @Override
    public ObservableList<Orders> getPrepareOrder() {


        ResultSet resultSet = orderRepository.getAllPrepareOrder();


        try {
            while(resultSet.next()){
                Orders.add(
                        new Orders(
                                resultSet.getString("CustID"),
                                resultSet.getString("OrderID"),
                                resultSet.getDate("OrderDate").toLocalDate(),
                                resultSet.getString("OrderStatus")
                        )
                );
            }

            return Orders;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean addOrderDetails(Orders orders,ObservableList<AddToCard> addToCardObservableList) {

        boolean isAdded=true;
        boolean productQtyUpdate=true;


        for(AddToCard row:addToCardObservableList){
            isAdded=orderDetailsRepository.addOrderDetails(new OrderDetails(orders.getOrderId(), row.getProductId(), row.getQty(), row.getDiscount()));

            double total=row.getQty() * row.getUnitPrice();

            addToCardEntityList.add(new AddToCardEntity(row.getProductId(),row.getProductName(),row.getUnitPrice(),row.getQty(),row.getDiscount(),total));

            productQtyUpdate = productQtyUpdate(row);

            if(isAdded!=true){
                return isAdded;
            }
            if(productQtyUpdate!=true){
                return productQtyUpdate;
            }
        }

        invoiceController.generateOrderInvoice(new InvoiceMainDetails(orders.getOrderId(),orders.getCustomerId()),addToCardEntityList);

        return isAdded;
    }

    private boolean productQtyUpdate(AddToCard row) {
        Product product = productService.searchProduct(row.getProductId());
        Integer qtyOnHand = product.getQtyOnHand();
        qtyOnHand=qtyOnHand-row.getQty();
        boolean updated = productService.updateDetails(new Product(product.getSupplierId(), product.getProductId(), product.getProductName(), product.getCategory(), product.getSize(), product.getUnitPrice(), qtyOnHand));
        return updated;
    }
}
