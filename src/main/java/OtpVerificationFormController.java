import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class OtpVerificationFormController {

    @FXML
    private JFXButton btnVerifyOtpCode;

    @FXML
    private Label lblEmail;

    @FXML
    private AnchorPane root;

    @FXML
    private AnchorPane sideAnchorPane;

    @FXML
    private TextField txtOtpCode;


    Alert errorAlert =new Alert(Alert.AlertType.ERROR);

    LoginFormController loginFormController=new LoginFormController();

    @FXML
    void btnVerifyOtpCodeOnAction(ActionEvent event) {

        Integer otpCodeText = Integer.parseInt(txtOtpCode.getText());


        if(OTP.otpNumber==otpCodeText){
            loginFormController.createMainform();
            LoginFormController.mainFormStageStatic.show();
            LoginFormController.verificationFormStageStatic.close();

        }
        else{
            errorAlert.setTitle("Error");
            errorAlert.setContentText("OTP is Invaild");
            errorAlert.show();
        }
    }

}
