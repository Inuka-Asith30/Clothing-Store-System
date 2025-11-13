package controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.dto.Product;
import service.Impl.ProductServiceImpl;
import service.ProductService;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductFormController implements Initializable {

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnReload;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private ComboBox<String> cmbCategory;

    @FXML
    private TableColumn<?, ?> colCategory;

    @FXML
    private TableColumn<?, ?> colPackSize;

    @FXML
    private TableColumn<?, ?> colProductId;

    @FXML
    private TableColumn<?, ?> colProductName;

    @FXML
    private TableColumn<?, ?> colQtyOnHand;

    @FXML
    private TableColumn<?, ?> colSupplierId;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private Label lblDate;

    @FXML
    private TableView<Product> tblProduct;

    @FXML
    private TextField txtPackSize;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtProductId;

    @FXML
    private TextField txtProductName;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtSearch;

    @FXML
    private TextField txtSupplierId;

    ObservableList<Product> productObservableList = FXCollections.observableArrayList();
    ProductService productService=new ProductServiceImpl();


    @FXML
    void btnAddOnAction(ActionEvent event) {
        String productIdText = txtProductId.getText();
        String nameText = txtProductName.getText();
        String discountText = txtPackSize.getText();
        String qtyText = txtQty.getText();
        String priceText = txtPrice.getText();
        String supplierIdText = txtSupplierId.getText();
        String categoryValue = cmbCategory.getValue();



    }

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnReloadOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("SupplierId"));
        colProductId.setCellValueFactory(new PropertyValueFactory<>("ProductId"));
        colProductName.setCellValueFactory(new PropertyValueFactory<>("ProductName"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("Category"));
        colPackSize.setCellValueFactory(new PropertyValueFactory<>("PackSize"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));

        loadInventoryDetails();

    }

    private void loadInventoryDetails() {
        productObservableList.clear();
        productObservableList=productService.getAllDetails();
        tblProduct.setItems(productObservableList);
    }
}
