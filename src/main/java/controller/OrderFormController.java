package controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.dto.AddToCard;
import model.dto.Orders;
import model.dto.Product;
import service.Impl.OrderServiceImpl;
import service.OrderService;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class OrderFormController implements Initializable {

    @FXML
    private JFXButton btnAddtoCard;

    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnPlaceOrder;

    @FXML
    private JFXButton btnPlaceOrder1;

    @FXML
    private ComboBox<String> cmbCategory;

    @FXML
    private ComboBox<String> cmbProductID;

    @FXML
    private TableColumn<?, ?> colCategory;

    @FXML
    private TableColumn<?, ?> colDiscount;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colProductId;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private Label lblNetTotal;

    @FXML
    private Label lblOrderDate;

    @FXML
    private Label lblOrderID;

    @FXML
    private TableView<AddToCard> tblAddToCard;

    @FXML
    private TextField txtDiscount;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtProductName;

    @FXML
    private TextField txtQty;

    Double netTotal=0.0;

    ObservableList<AddToCard> addToCardObservableList= FXCollections.observableArrayList();

    OrderService orderService=new OrderServiceImpl();

    Alert InformationAlert =new Alert(Alert.AlertType.INFORMATION);

    @FXML
    void btnAddtoCardOnAction(ActionEvent event) {
        String productIDValue = cmbProductID.getValue();
        String nameText = txtProductName.getText();
        Integer qtyText = Integer.parseInt(txtQty.getText());
        Double priceText = Double.parseDouble(txtPrice.getText());
        String categoryValue = cmbCategory.getValue();
        Integer discountText = Integer.parseInt(txtDiscount.getText());

        addToCardObservableList.add(new AddToCard(productIDValue,nameText,categoryValue,priceText,qtyText,discountText));


        netTotal=netTotal+(priceText*qtyText);

        lblNetTotal.setText(String.valueOf(netTotal));

        loadAddtoCardTable();



    }

    private void loadAddtoCardTable() {
        tblAddToCard.setItems(addToCardObservableList);
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void cmbCategoryOnAction(ActionEvent event) {
        String categoryValue = cmbCategory.getValue();

        cmbProductID.setItems(orderService.getProductsId(categoryValue));

    }

    private void clearTable() {
        addToCardObservableList.clear();
        tblAddToCard.setItems(addToCardObservableList);
    }

    @FXML
    void cmbProductIDOnAction(ActionEvent event) {
        String productIDValue = cmbProductID.getValue();

        Product product=orderService.searchProduct(productIDValue);

        txtProductName.setText(product.getProductName());
        txtPrice.setText(String.valueOf(product.getUnitPrice()));


    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {


        String orderIDText = lblOrderID.getText();
        LocalDate localDate = LocalDate.parse(lblOrderDate.getText());
        String orderStatus="Preparing";

        Orders orders=new Orders(orderIDText,localDate,orderStatus);

        boolean isAdded=orderService.addOrder(orders,addToCardObservableList);

        if(isAdded){
            InformationAlert.setContentText("Added is successfully");

            InformationAlert.show();

            lblOrderID.setText(orderService.getNewOrderId());
            clearTable();
        }
        else{
            InformationAlert.setContentText("Added is not successfully");

            InformationAlert.show();
        }


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colProductId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));

        lblOrderDate.setText(GetDate.getDate());

        ObservableList<String>  categoryList=FXCollections.observableArrayList("Grocery","Beverages","Household","Vegetables","Fruits");

        cmbCategory.setItems(categoryList);

        lblOrderID.setText(orderService.getNewOrderId());

        txtProductName.setEditable(false);
        txtPrice.setEditable(false);

    }
}
