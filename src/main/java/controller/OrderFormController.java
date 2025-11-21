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
import model.dto.Customer;
import model.dto.Orders;
import model.dto.Product;
import service.CustomerService;
import service.Impl.CustomerServiceImpl;
import service.Impl.OrderServiceImpl;
import service.OrderService;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class OrderFormController implements Initializable {

    @FXML
    private JFXButton btnClearTable;

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
    private TextField txtCustomerName;

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
    private TextField txtPhoneNumber;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtProductName;

    @FXML
    private TextField txtQty;

    Double netTotal=0.0;

    Customer customer=null;

    ObservableList<AddToCard> addToCardObservableList= FXCollections.observableArrayList();

    CustomerService customerService=new CustomerServiceImpl();

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

        AddToCard toCard = new AddToCard(productIDValue, nameText, categoryValue, priceText, qtyText, discountText);

//        if(productIDValue==null){
//            InformationAlert.setContentText("Please select Product Id");
//        }
//        else if(categoryValue==null){
//            InformationAlert.setContentText("Please select Category");
//        }
//        else if(txtQty.getText()==null){
//            InformationAlert.setContentText("Please enter Quantity");
//        }
//        else if(txtDiscount.getText().equals("")){
//            InformationAlert.setContentText("Please enter discount");
//        }
//        else{
//
//        }

        int index=0;

        boolean isEquated=false;

        for(AddToCard addToCard : addToCardObservableList){

            if(addToCard.getProductId().equals(toCard.getProductId())){
                Integer totalQty=addToCard.getQty()+toCard.getQty();
                addToCard.setQty(totalQty);
                addToCardObservableList.remove(index);
                addToCardObservableList.add(index,addToCard);

                isEquated=true;
                break;
            }
            index=index+1;

        }
        if(isEquated!=true){
           addToCardObservableList.add(toCard);
        }
        loadAddtoCardTable();
        netTotal=netTotal+(priceText*qtyText);
        lblNetTotal.setText(String.valueOf(netTotal));
        btnPlaceOrder.setDisable(false);








    }

    private void loadAddtoCardTable() {
        tblAddToCard.refresh();
        tblAddToCard.setItems(addToCardObservableList);
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtProductName.setText(null);
        cmbProductID.setValue(null);
        cmbCategory.setValue(null);
        txtPrice.setText(null);
        txtQty.setText(null);
        txtDiscount.setText(null);
    }

    @FXML
    void btnClearTableOnAction(ActionEvent event) {
        clearTable();
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

        Orders orders=new Orders(customer.getCustomerId(),orderIDText,localDate,orderStatus);

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
        btnPlaceOrder.setDisable(true);

    }
    @FXML
    void txtPhoneNumberOnAction(ActionEvent event) {
        customer = customerService.searchCustomerbyPhoneNo(txtPhoneNumber.getText());


        if(customer==null){
            InformationAlert.setContentText("Customer is not found");

            InformationAlert.show();
            txtCustomerName.setText(null);
        }
        else{
            txtCustomerName.setText(customer.getName());
        }
    }

}
