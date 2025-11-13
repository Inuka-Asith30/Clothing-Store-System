package controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.dto.Supplier;
import service.Impl.SupplierServiceImpl;
import service.SupplierService;

import java.net.URL;
import java.util.ResourceBundle;

public class SupplierFormController implements Initializable {

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
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPhoneNumber;

    @FXML
    private TableColumn<?, ?> colSupplierId;

    @FXML
    private Label lblOrderDate1;

    @FXML
    private TableView<Supplier> tblSupplier;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPhoneNumber;

    @FXML
    private TextField txtSearch;

    @FXML
    private TextField txtSupplierId;

    @FXML
    private TextField txtSupplierName;

    @FXML
    private Label lblDate;

    Alert informationAlert =new Alert(Alert.AlertType.INFORMATION);

    ObservableList<Supplier> supplierObservableList= FXCollections.observableArrayList();

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String supplierIdText = txtSupplierId.getText();
        String addressText = txtAddress.getText();
        String emailText = txtEmail.getText();
        String nameText = txtSupplierName.getText();
        String phoneNumberText = txtPhoneNumber.getText();

        boolean isAdded=supplierService.addSupplierDetails(new Supplier(supplierIdText,nameText,addressText,emailText,phoneNumberText));

        if(isAdded){
            informationAlert.setContentText("Added is successfully");
            informationAlert.show();

            loadSupplierdetails();
        }
        else{
            informationAlert.setContentText("Added is not successfully");
            informationAlert.show();
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtSupplierName.setText(null);
        txtSupplierId.setText(null);
        txtPhoneNumber.setText(null);
        txtEmail.setText(null);
        txtAddress.setText(null);
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String supplierIdText = txtSupplierId.getText();

        boolean isDeleted = supplierService.deleteSupplierDetails(supplierIdText);

        if(isDeleted){
            informationAlert.setContentText("Deleted is successfully");
            informationAlert.show();

            loadSupplierdetails();
        }
        else{
            informationAlert.setContentText("Deleted is not successfully");
            informationAlert.show();
        }
    }

    @FXML
    void btnNewIdOnAction(ActionEvent event) {

    }

    @FXML
    void btnReloadOnAction(ActionEvent event) {
        loadSupplierdetails();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

        String supplierIdText = txtSupplierId.getText();
        String addressText = txtAddress.getText();
        String emailText = txtEmail.getText();
        String nameText = txtSupplierName.getText();
        String phoneNumberText = txtPhoneNumber.getText();

        boolean isUpdated=supplierService.updateSupplierDetails(new Supplier(supplierIdText,nameText,addressText,emailText,phoneNumberText));

        if(isUpdated){
            informationAlert.setContentText("Updated is successfully");
            informationAlert.show();

            loadSupplierdetails();
        }
        else{
            informationAlert.setContentText("Updated is not successfully");
            informationAlert.show();
        }
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {

    }

    SupplierService supplierService=new SupplierServiceImpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        informationAlert.setTitle("Information");
        informationAlert.setHeaderText("Information");


        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        loadSupplierdetails();

        tblSupplier.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) ->{
            if(newValue!=null){
                setSelectedValue(newValue);

            }
        }));

        lblDate.setText(GetDate.getDate());
    }

    private void setSelectedValue(Supplier newValue) {

        txtSupplierId.setText(newValue.getSupplierId());
        txtAddress.setText(newValue.getAddress());
        txtEmail.setText(newValue.getEmail());
        txtSupplierName.setText(newValue.getName());
        txtPhoneNumber.setText(newValue.getPhoneNumber());

    }

    private void loadSupplierdetails() {

        supplierObservableList.clear();
        supplierObservableList=supplierService.getAllDetails();
        tblSupplier.setItems(supplierObservableList);
    }


}
