package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SupplierFormController {

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
    private JFXComboBox<?> cmbProductID;

    @FXML
    private Label lblOrderDate;

    @FXML
    private JFXTreeTableView<?> tblInventory;

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

}
