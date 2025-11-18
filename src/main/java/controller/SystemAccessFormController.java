package controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.dto.SystemAccess;
import service.Impl.SystemAccessServiceImpl;
import service.SystemAccessService;

import java.net.URL;
import java.util.ResourceBundle;

public class SystemAccessFormController implements Initializable {

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TextField txtEmployeeId;

    @FXML
    private TextField txtPassword;

    @FXML
    private TableColumn<?, ?> colEmployeeId;

    @FXML
    private TableColumn<?, ?> colPassword;

    @FXML
    private TableView<SystemAccess> tblSystemAccess;


    SystemAccessService systemAccessService=new SystemAccessServiceImpl();

    ObservableList<SystemAccess> systemAccessObservableList= FXCollections.observableArrayList();

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
    void btnUpdateOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));

        loadTableDetails();
    }

    private void loadTableDetails() {
        systemAccessObservableList.clear();
        systemAccessObservableList = systemAccessService.getAllDetails();
        tblSystemAccess.setItems(systemAccessObservableList);
    }
}
