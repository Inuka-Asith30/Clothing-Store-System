package controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.dto.Customer;
import service.CustomerService;
import service.Impl.CustomerServiceImpl;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerFormController implements Initializable {

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnNewId;

    @FXML
    private JFXButton btnReload;

    @FXML
    private JFXButton btnUpdate;


    @FXML
    private ComboBox<String> cmbTitle;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colCustomerId;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPhoneNumber;

    @FXML
    private TableColumn<?, ?> colTitle;

    @FXML
    private Label lblOrderDate;

    @FXML
    private TableView<Customer> tblCustomer;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtCustomerId;

    @FXML
    private TextField txtCustomerName;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPhoneNumber;

    @FXML
    private TextField txtSearch;

    ObservableList<Customer> customerObservableList= FXCollections.observableArrayList();

    CustomerService customerService =new CustomerServiceImpl();

    Alert InformationAlert =new Alert(Alert.AlertType.INFORMATION);

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String customerId = txtCustomerId.getText();
        String customerName = txtCustomerName.getText();
        String email = txtEmail.getText();
        String phoneNumber = txtPhoneNumber.getText();
        String address = txtAddress.getText();
        String title = cmbTitle.getValue();

        boolean isAdded = customerService.addCustomerDetails(new Customer(customerId, title, customerName, address, email, phoneNumber));

        if(isAdded){
            InformationAlert.setContentText("Added is successfully");

            InformationAlert.show();

            loadCustomerDetails();
        }
        else {
            InformationAlert.setContentText("Added is not successfully");

            InformationAlert.show();
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtCustomerId.setText(null);
        txtCustomerName.setText(null);
        txtEmail.setText(null);
        txtPhoneNumber.setText(null);
        txtAddress.setText(null);
        cmbTitle.setValue(null);
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

        String customerId = txtCustomerId.getText();
        boolean isDeleted = customerService.deleteCustomerDetails(customerId);

        if(isDeleted){
            InformationAlert.setContentText("Deleted is successfully");
            InformationAlert.show();

            loadCustomerDetails();
        }
        else{
            InformationAlert.setContentText("Deleted is not successfully");

            InformationAlert.show();
        }
    }

    @FXML
    void btnNewIdOnAction(ActionEvent event) {
        txtCustomerId.setText(customerService.getNewCustomerID());
    }

    @FXML
    void btnReloadOnAction(ActionEvent event) {
        loadCustomerDetails();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String customerId = txtCustomerId.getText();
        String customerName = txtCustomerName.getText();
        String email = txtEmail.getText();
        String phoneNumber = txtPhoneNumber.getText();
        String address = txtAddress.getText();
        String title = cmbTitle.getValue();

        boolean isUpdated = customerService.updateCustDetails(new Customer(customerId, title, customerName, address, email, phoneNumber));

        if(isUpdated){
            InformationAlert.setContentText("Updated is successfully");

            InformationAlert.show();
            loadCustomerDetails();
        }
        else{
            InformationAlert.setContentText("Updated is not successfully");

            InformationAlert.show();
        }
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {
        String custId = txtSearch.getText();
        Customer customer = customerService.searchCustomerbyId(custId);
        customerObservableList.clear();
        customerObservableList.add(customer);
        tblCustomer.setItems(customerObservableList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        loadCustomerDetails();

        ObservableList<String> titleList=FXCollections.observableArrayList("Mr","Mrs","Miss");
        cmbTitle.setItems(titleList);

        tblCustomer.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) ->{
            if(newValue!=null){
                setSelectedValue(newValue);

            }
        }));

    }

    private void setSelectedValue(Customer newValue) {
        txtCustomerId.setText(newValue.getCustomerId());
        txtCustomerName.setText(newValue.getName());
        txtEmail.setText(newValue.getEmail());
        txtPhoneNumber.setText(newValue.getPhoneNumber());
        txtAddress.setText(newValue.getAddress());
        cmbTitle.setValue(newValue.getTitle());
    }

    private void loadCustomerDetails() {
        customerObservableList.clear();
        customerObservableList = customerService.getCustomerDetails();
        tblCustomer.setItems(customerObservableList);
    }
}
