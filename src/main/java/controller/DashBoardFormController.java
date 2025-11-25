package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.dto.Orders;
import service.CustomerService;
import service.Impl.CustomerServiceImpl;
import service.Impl.LoginFormServiceImpl;
import service.Impl.OrderServiceImpl;
import service.OrderService;

import java.net.URL;
import java.util.ResourceBundle;

public class DashBoardFormController implements Initializable {

    @FXML
    private Button btnSubmit;

    @FXML
    private ComboBox<String> cmbOrderStatus;

    @FXML
    private TableColumn<?, ?> colCustomerId;

    @FXML
    private TableColumn<?, ?> colOrderDate;

    @FXML
    private TableColumn<?, ?> colOrderId;

    @FXML
    private TableColumn<?, ?> colOrderStatus;

    @FXML
    private AnchorPane customerBox;

    @FXML
    private AnchorPane deliveredBox;

    @FXML
    private Label lblCustomerCount;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblDeliOrderCount;

    @FXML
    private Label lblName;

    @FXML
    private Label lblPreOrderCount;

    @FXML
    private AnchorPane preparingBox;

    @FXML
    private TableView<Orders> tblPreparingOrder;

    @FXML
    private TextField txtOrderId;

    CustomerService customerService=new CustomerServiceImpl();

    OrderService orderService=new OrderServiceImpl();

    ObservableList<Orders> orders = FXCollections.observableArrayList();

    @FXML
    void btnSubmitOnAction(ActionEvent event) {
        String orderStatus = cmbOrderStatus.getValue();
        String orderId = txtOrderId.getText();

        if(orderStatus.equals("Canceled")){
            orderService.deleteOrder(orderId);
            loadOrderDetails();
        }
        else{
            orderService.updateOrder(orderId,orderStatus);
            loadOrderDetails();
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblName.setText(LoginFormServiceImpl.getEmployee.getName()+",welcome back!");
        lblDate.setText(GetDate.getDate());
        lblCustomerCount.setText(String.valueOf(customerService.getCount()));


        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colOrderDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        colOrderStatus.setCellValueFactory(new PropertyValueFactory<>("orderStatus"));

        ObservableList<String> orderStatusList=FXCollections.observableArrayList("Canceled","Delivered","Preparing");

        cmbOrderStatus.setItems(orderStatusList);

        loadOrderDetails();

        tblPreparingOrder.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) ->{
            if(newValue!=null){
                setSelectedValue(newValue);

            }
        }));
    }

    private void setSelectedValue(Orders newValue) {
        txtOrderId.setText(newValue.getOrderId());
        cmbOrderStatus.setValue(newValue.getOrderStatus());
    }

    private void loadOrderDetails() {
        orders.clear();
        orders=orderService.getPrepareOrder();
        tblPreparingOrder.setItems(orders);
        lblPreOrderCount.setText(orders.size()+"");
    }
}
