package controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.dto.Employee;
import service.EmployeeService;
import service.Impl.EmployeeServiceImpl;

import java.io.IOException;
import java.net.URL;
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
    private JFXButton btnLoginAccess;

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

    @FXML
    private JFXButton btnNewId;


    EmployeeService employeeService=new EmployeeServiceImpl();

    ObservableList<Employee> employeeDetails= FXCollections.observableArrayList();

    Stage loginAccessFormStage=new Stage();


    Alert myAlert=new Alert(Alert.AlertType.ERROR);
    Alert InformationAlert =new Alert(Alert.AlertType.INFORMATION);


    @FXML
    void btnAddOnAction(ActionEvent event) {
        String employeeId = txtEmployeeId.getText();
        String employeeName = txtEmployeeName.getText();
        String email = txtEmail.getText();
        Double salary = Double.parseDouble(txtSalary.getText());
        String position = txtPosition.getText();
        String phoneNumber = txtPhoneNumber.getText();
        String address = txtAddress.getText();
        String title = cmbTitle.getValue();

        boolean isAdded=employeeService.addEmployeeDetails(new Employee(employeeId,title,employeeName,salary,address,position,email,phoneNumber));

        if(isAdded){
            InformationAlert.setContentText("Added is successfully");

            InformationAlert.show();

            loadEmpDetails();
        }
        else {
            InformationAlert.setContentText("Added is not successfully");

            InformationAlert.show();
        }


    }

    @FXML
    void btnNewIdOnAction(ActionEvent event) {
        txtEmployeeId.setText(employeeService.getLastEmployeeID());
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

        String employeeId = txtEmployeeId.getText();

        boolean isDeleted = employeeService.deleteEmployeeDetails(employeeId);

        if(isDeleted){
            InformationAlert.setContentText("Deleted is successfully");
            InformationAlert.show();

            loadEmpDetails();
        }
        else{
            InformationAlert.setContentText("Deleted is not successfully");

            InformationAlert.show();
        }

    }

    @FXML
    void btnReloadOnAction(ActionEvent event) {
        loadEmpDetails();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String employeeId = txtEmployeeId.getText();
        String employeeName = txtEmployeeName.getText();
        String email = txtEmail.getText();
        Double salary = Double.parseDouble(txtSalary.getText());
        String position = txtPosition.getText();
        String phoneNumber = txtPhoneNumber.getText();
        String address = txtAddress.getText();
        String title = cmbTitle.getValue();

        boolean isUpdated=employeeService.updateEmloyeeDetails(new Employee(employeeId,title,employeeName,salary,address,position,email,phoneNumber));

        if(isUpdated){
            InformationAlert.setContentText("Updated is successfully");

            InformationAlert.show();
            loadEmpDetails();
        }
        else{
            InformationAlert.setContentText("Updated is not successfully");

            InformationAlert.show();
        }

    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {

    }


    @FXML
    void btnLoginAccessOnAction(ActionEvent event) {
        try {
            loginAccessFormStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/SystemAccess.fxml"))));
            loginAccessFormStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        InformationAlert.setTitle("Information");
        InformationAlert.setHeaderText("Information");

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

        lblOrderDate.setText(GetDate.getDate());
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
