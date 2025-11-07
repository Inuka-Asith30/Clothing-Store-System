package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class OrderFormController {

    @FXML
    private JFXButton btnAddtoCard;

    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnPlaceOrder;

    @FXML
    private JFXButton btnPlaceOrder1;

    @FXML
    private ComboBox<?> cmbProductID;

    @FXML
    private Label lblNetTotal;

    @FXML
    private Label lblOrderDate;

    @FXML
    private Label lblOrderID;

    @FXML
    private JFXTreeTableView<?> tblAddToCard;

    @FXML
    private TextField txtCategory;

    @FXML
    private TextField txtDiscount;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtProductName;

    @FXML
    private TextField txtQty;

    @FXML
    void btnAddtoCardOnAction(ActionEvent event) {

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void cmbProductIDOnAction(ActionEvent event) {

    }

}
