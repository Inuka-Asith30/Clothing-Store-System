import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import service.LoginFormService;
import service.Impl.LoginFormServiceImpl;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginFormController implements Initializable {

    @FXML
    private Hyperlink btnForgotPassword;

    @FXML
    private JFXButton btnSubmit;

    @FXML
    private AnchorPane root;

    @FXML
    private AnchorPane sideAnchorPane;

    @FXML
    private TextField txtEmployeeId;

    @FXML
    private Label lblGreeting;

    @FXML
    private PasswordField txtPassword;

    private Pane view;

    Stage mainFormStage=new Stage();
    Starter starter=new Starter();
    public static Stage mainFormStageStatic;
    Stage verificationFormStage=new Stage();

    public static Stage verificationFormStageStatic;

    Alert ErrorAlert =new Alert(Alert.AlertType.ERROR);

    LoginFormService loginFormService=new LoginFormServiceImpl();

    @FXML
    void btnSubmitOnAction(ActionEvent event) {
        String employeeID=txtEmployeeId.getText();
        String password = txtPassword.getText();

        boolean isValidated=loginFormService.validateSubmit(employeeID,password);

        if(isValidated){

            createMainform();



            Starter.loginFormstage.close();

            txtEmployeeId.setText(null);
            txtPassword.setText(null);




        }
        else{
            ErrorAlert.setContentText("Invaild Login");

            ErrorAlert.show();

            txtEmployeeId.setText(null);
            txtPassword.setText(null);
        }
    }

    public void createMainform() {

        try {
            mainFormStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        mainFormStage.show();
        mainFormStage.setResizable(false);
        mainFormStageStatic=mainFormStage;

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblGreeting.setText(loginFormService.getTime());
    }

    @FXML
    void btnForgotPasswordOnAction(ActionEvent event) throws IOException {
        verificationFormStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/SendEmailForVerificationForm.fxml"))));
        verificationFormStage.show();
        Starter.loginFormstage.close();
        verificationFormStageStatic=verificationFormStage;

    }

}
