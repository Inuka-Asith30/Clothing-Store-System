import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import model.dto.Employee;
import service.EmployeeService;
import service.Impl.EmployeeServiceImpl;
import service.Impl.LoginFormServiceImpl;

import java.io.IOException;

public class SendEmailForVerificationFormController {

    @FXML
    private JFXButton btnBackToLogin;

    @FXML
    private JFXButton btnSendEmail;

    @FXML
    private AnchorPane root;

    @FXML
    private AnchorPane sideAnchorPane;

    @FXML
    private BorderPane mainBorder;


    public Pane otpformView;

    @FXML
    private TextField txtEmail;

    EmployeeService employeeService=new EmployeeServiceImpl();

    SendOtpThroughGmail sendOtpThroughGmail=new SendOtpThroughGmail();
    Alert errorAlert =new Alert(Alert.AlertType.ERROR);



    @FXML
    void SendEmailOnAction(ActionEvent event) {
        String emailText = txtEmail.getText();
        Employee employee = employeeService.searchEmployeeByEmail(emailText);

        if(employee!=null){
            try {
                otpformView= FXMLLoader.load(getClass().getResource("/view/OtpVerificationForm.fxml"));
                mainBorder.setCenter(otpformView);
                sendOtpThroughGmail.sendMessage(emailText);
                LoginFormServiceImpl.getEmployee=employee;


            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else{
            errorAlert.setTitle("Error");
            errorAlert.setContentText("Email is not found");
            errorAlert.show();
        }
    }

    @FXML
    void btnBackToLoginOnAction(ActionEvent event) {
        Starter.loginFormstage.show();
    }

}
