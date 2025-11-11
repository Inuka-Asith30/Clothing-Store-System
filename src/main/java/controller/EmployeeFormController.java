package controller;

import DB.DBConnection;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.dto.Employee;
import service.EmployeeService;
import service.EmployeeServiceImpl;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EmployeeFormController implements Initializable {

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
    private ComboBox<String> cmbTitle;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colEmployeeId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPhoneNumber;

    @FXML
    private TableColumn<?, ?> colPosition;

    @FXML
    private TableColumn<?, ?> colSalary;

    @FXML
    private TableColumn<?, ?> colTitle;

    @FXML
    private Label lblOrderDate;

    @FXML
    private TableView<Employee> tblEmployee;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtEmployeeId;

    @FXML
    private TextField txtEmployeeName;

    @FXML
    private TextField txtPhoneNumber;

    @FXML
    private TextField txtPosition;

    @FXML
    private TextField txtSalary;

    @FXML
    private TextField txtSearch;

    EmployeeService employeeService=new EmployeeServiceImpl();

    ObservableList<Employee> employeeDetails= FXCollections.observableArrayList();


    Alert myAlert=new Alert(Alert.AlertType.ERROR);

    @FXML
    void btnAddOnAction(ActionEvent event) {

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtAddress.setText(null);
        txtEmployeeName.setText(null);
        txtPosition.setText(null);
        txtEmail.setText(null);
        txtSalary.setText(null);

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

        myAlert.setTitle("This is the alert title");
        myAlert.setHeaderText("This is the header text");
        myAlert.setContentText("This is the alert content");

        myAlert.show();
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
        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        colPosition.setCellValueFactory(new  PropertyValueFactory<>("position"));

        loadEmpDetails();

        tblEmployee.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) ->{
            if(newValue!=null){
                setSelectedValue(newValue);

            }
        }));
    }

    private void setSelectedValue(Employee newValue) {
        txtEmployeeId.setText(newValue.getEmployeeId());
        txtEmployeeName.setText(newValue.getName());
        txtEmail.setText(newValue.getEmail());
        txtSalary.setText(String.valueOf(newValue.getSalary()));
        txtPosition.setText(newValue.getPosition());
        txtPhoneNumber.setText(newValue.getPhoneNumber());
        txtAddress.setText(newValue.getAddress());
        cmbTitle.setValue(newValue.getTitle());
    }

    private void loadEmpDetails() {

        employeeDetails.clear();
        employeeDetails = employeeService.getEmployeeDetails();
        tblEmployee.setItems(employeeDetails);
    }



}
