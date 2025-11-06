package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OrderFormController implements Initializable {

    @FXML
    private Button btnDashBoard;

    @FXML
    private Button btnEmployee;

    @FXML
    private Button btnInventory;

    @FXML
    private JFXButton btnLogOut;

    @FXML
    private Button btnOrder;

    @FXML
    private Button btnSupplier;

    @FXML
    private Label lblEmployeeName;

    @FXML
    private Label lblEmpoyeeID;

    @FXML
    private BorderPane mainPane;

    private Pane view;

    @FXML
    void btnOrderOnAction(ActionEvent event) throws IOException {
        view=new FXMLLoader().load(getClass().getResource("/view/index.fxml"));
        mainPane.setCenter(view);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
