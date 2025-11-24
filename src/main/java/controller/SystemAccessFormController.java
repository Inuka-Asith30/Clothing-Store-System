package controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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

    Alert informationAlert =new Alert(Alert.AlertType.INFORMATION);

    SystemAccessService systemAccessService=new SystemAccessServiceImpl();

    ObservableList<SystemAccess> systemAccessObservableList= FXCollections.observableArrayList();

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String employeeIdText = txtEmployeeId.getText();
        String passwordText = txtPassword.getText();
        boolean isAdded = systemAccessService.addDetails(new SystemAccess(employeeIdText, passwordText));

        if(isAdded){
            informationAlert.setContentText("Added is successfully");
            informationAlert.show();

            loadTableDetails();
        }
        else{
            informationAlert.setContentText("Added is not successfully");
            informationAlert.show();
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtEmployeeId.setText(null);
        txtPassword.setText(null);
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String employeeIdText = txtEmployeeId.getText();

        boolean isDeleted = systemAccessService.deleteDetails(employeeIdText);

        if(isDeleted){
            informationAlert.setContentText("Deleted is successfully");
            informationAlert.show();

            loadTableDetails();
        }
        else{
            informationAlert.setContentText("Deleted is not successfully");
            informationAlert.show();
        }


    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String employeeIdText = txtEmployeeId.getText();
        String passwordText = txtPassword.getText();

        boolean isUpdated = systemAccessService.updateDetails(new SystemAccess(employeeIdText, passwordText));

        if(isUpdated){
            informationAlert.setContentText("Updated is successfully");
            informationAlert.show();

            loadTableDetails();
        }
        else{
            informationAlert.setContentText("Updated is not successfully");
            informationAlert.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));

        loadTableDetails();

        tblSystemAccess.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) ->{
            if(newValue!=null){
                setSelectedValue(newValue);

            }
        }));
    }

    private void setSelectedValue(SystemAccess newValue) {
        txtEmployeeId.setText(newValue.getEmployeeId());
        txtPassword.setText(newValue.getPassword());
    }

    private void loadTableDetails() {
        systemAccessObservableList.clear();
        systemAccessObservableList = systemAccessService.getAllDetails();
        tblSystemAccess.setItems(systemAccessObservableList);
    }
}
