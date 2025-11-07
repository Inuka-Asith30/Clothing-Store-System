package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class InventoryFormController {

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
    private ComboBox<?> cmbProductID;

    @FXML
    private Label lblOrderDate;

    @FXML
    private JFXTreeTableView<?> tblInventory;

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
    private TextField txtSearch;

    @FXML
    void btnAddOnAction(ActionEvent event) {

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
    void cmbProductIDOnAction(ActionEvent event) {

    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {

    }

}
