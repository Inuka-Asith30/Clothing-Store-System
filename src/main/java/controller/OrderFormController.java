package controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.dto.AddToCard;

import java.net.URL;
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

    ObservableList<AddToCard> addToCardObservableList= FXCollections.observableArrayList();

    @FXML
    void btnAddtoCardOnAction(ActionEvent event) {
        String productIDValue = cmbProductID.getValue();
        String nameText = txtProductName.getText();
        Integer qtyText = Integer.parseInt(txtQty.getText());
        Double priceText = Double.parseDouble(txtPrice.getText());
        String categoryValue = cmbCategory.getValue();
        Integer discountText = Integer.parseInt(txtDiscount.getText());

        addToCardObservableList.add(new AddToCard(productIDValue,nameText,categoryValue,priceText,qtyText,discountText));

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

    }

    @FXML
    void cmbProductIDOnAction(ActionEvent event) {

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
    }
}
