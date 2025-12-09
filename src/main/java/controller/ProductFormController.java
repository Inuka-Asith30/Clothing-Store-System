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

    public JFXButton btnNewId;
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

    Alert informationAlert =new Alert(Alert.AlertType.INFORMATION);


    @FXML
    void btnAddOnAction(ActionEvent event) {
        String productIdText = txtProductId.getText();
        String nameText = txtProductName.getText();
        String packSizeText = txtPackSize.getText();
        Integer qtyText = Integer.parseInt(txtQty.getText());
        Double priceText = Double.parseDouble(txtPrice.getText());
        String supplierIdText = txtSupplierId.getText();
        String categoryValue = cmbCategory.getValue();

        boolean isAdded=productService.addDetails(new Product(supplierIdText,productIdText,nameText,categoryValue,packSizeText,priceText,qtyText));

        if(isAdded){
            informationAlert.setContentText("Added is successfully");
            informationAlert.show();

            loadProductDetails();
        }
        else{
            informationAlert.setContentText("Added is not successfully");
            informationAlert.show();
        }

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtProductId.setText(null);
        txtProductName.setText(null);
        txtPackSize.setText(null);
        txtQty.setText(null);
        txtPrice.setText(null);
        txtSupplierId.setText(null);
        cmbCategory.setValue(null);
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String productIdText = txtProductId.getText();

        boolean deleted = productService.deleteDetails(productIdText);

        if(deleted){
            informationAlert.setContentText("deleted is successfully");
            informationAlert.show();

            loadProductDetails();
        }
        else{
            informationAlert.setContentText("deleted is not successfully");
            informationAlert.show();
        }
    }

    @FXML
    void btnReloadOnAction(ActionEvent event) {
        loadProductDetails();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String productIdText = txtProductId.getText();
        String nameText = txtProductName.getText();
        String packSizeText = txtPackSize.getText();
        Integer qtyText = Integer.parseInt(txtQty.getText());
        Double priceText = Double.parseDouble(txtPrice.getText());
        String supplierIdText = txtSupplierId.getText();
        String categoryValue = cmbCategory.getValue();

        boolean isUpdated=productService.updateDetails(new Product(supplierIdText,productIdText,nameText,categoryValue,packSizeText,priceText,qtyText));

        if(isUpdated){
            informationAlert.setContentText("Updated is successfully");
            informationAlert.show();

            loadProductDetails();
        }
        else{
            informationAlert.setContentText("Updated is not successfully");
            informationAlert.show();
        }

    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {
        Product product = productService.searchProduct(txtSearch.getText());

        productObservableList.clear();
        productObservableList.add(product);
        tblProduct.setItems(productObservableList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colProductId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        colProductName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colPackSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));

        loadProductDetails();

        tblProduct.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) ->{
            if(newValue!=null){
                setSelectedValue(newValue);

            }
        }));

        lblDate.setText(GetDate.getDate());

        ObservableList<String>  categoryList=FXCollections.observableArrayList("Mens-Fashion","Womens-Fashion");

        cmbCategory.setItems(categoryList);
    }

    private void setSelectedValue(Product newValue) {

        txtProductId.setText(newValue.getProductId());
        txtProductName.setText(newValue.getProductName());
        txtPackSize.setText(newValue.getSize());
        txtQty.setText(String.valueOf(newValue.getQtyOnHand()));
        txtPrice.setText(String.valueOf(newValue.getUnitPrice()));
        txtSupplierId.setText(newValue.getSupplierId());
        cmbCategory.setValue(newValue.getCategory());

    }

    private void loadProductDetails() {
        productObservableList.clear();
        productObservableList=productService.getAllDetails();
        tblProduct.setItems(productObservableList);
    }

    public void btnNewIdOnAction(ActionEvent actionEvent) {
        txtProductId.setText(productService.getNewId());

    }
}
