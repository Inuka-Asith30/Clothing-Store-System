import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Starter extends Application {

    public Stage loginFormstage;
    private Stage stage;

    public static void main(String[] args){
        launch();
    }

    public void closeLoginForm(){
        System.out.println(this.loginFormstage);
        //loginFormstage.close();
    }

    @Override
    public void start(Stage stage) throws Exception {


        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/LoginForm.fxml"))));
        stage.show();
        loginFormstage=stage;

        System.out.println(loginFormstage);
    }


}
