import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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

    Alert ErrorAlert =new Alert(Alert.AlertType.ERROR);

    LoginFormService loginFormService=new LoginFormServiceImpl();

    @FXML
    void btnSubmitOnAction(ActionEvent event) {
        String employeeID=txtEmployeeId.getText();
        String password = txtPassword.getText();

        boolean isValidated=loginFormService.validateSubmit(employeeID,password);

        if(isValidated){
            try {
                mainFormStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"))));
                mainFormStage.show();
                mainFormStage.setResizable(false);
                Starter.loginFormstage.close();
                mainFormStageStatic=mainFormStage;

                txtEmployeeId.setText(null);
                txtPassword.setText(null);


            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        else{
            ErrorAlert.setContentText("Invaild Login");

            ErrorAlert.show();

            txtEmployeeId.setText(null);
            txtPassword.setText(null);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblGreeting.setText(loginFormService.getTime());
    }

}
