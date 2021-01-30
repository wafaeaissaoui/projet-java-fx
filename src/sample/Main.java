package sample;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    public static void showInformationAlertBox(String s) {
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("/sample/views2/login.fxml"));
       // Parent root = FXMLLoader.load(getClass().getResource("/sample/views/login.fxml"));
        primaryStage.getIcons().add(new Image("/sample/assests/image/finallogo.png"));
        primaryStage.setTitle("login user ");
        primaryStage.setScene(new Scene(root));

        //set stage borderless
        primaryStage.initStyle(StageStyle.UNDECORATED);

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
