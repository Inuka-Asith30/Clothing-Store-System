package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class OrderFormController {

    @FXML
    private JFXButton btnAddtoCard;

    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnPlaceOrder;

    @FXML
    private JFXComboBox<?> cmbProductID;

    @FXML
    private Label lblNetTotal;

    @FXML
    private Label lblOrderDate;

    @FXML
    private Label lblOrderID;

    @FXML
    private JFXTreeTableView<?> tblAddToCard;

    @FXML
    private JFXTextField txtCategory;

    @FXML
    private JFXTextField txtDiscount;

    @FXML
    private JFXTextField txtPrice;

    @FXML
    private JFXTextField txtProductName;

    @FXML
    private JFXTextField txtQty;

    @FXML
    void btnAddtoCardOnAction(ActionEvent event) {

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

}
