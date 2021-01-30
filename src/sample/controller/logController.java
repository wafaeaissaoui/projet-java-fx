package sample.controller;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.StageStyle;
import sample.DataValidation.Datavalidation;
import sample.assests.db.Dbconnect;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class logController implements Initializable {
    @FXML
    private Button closeButton;

    @FXML
    private Label label1;

    @FXML
    private Label label2;

    @FXML
    private JFXTextField tf_username;

    @FXML
    private PasswordField pf_password;
    @FXML
    public void login(ActionEvent event) throws SQLException, IOException {

        Connection connection= Dbconnect.getInstance().getConnection();
        String username,password;
        username=tf_username.getText();
        password=pf_password.getText();
        Statement statement= connection.createStatement();
 if (username.isEmpty() && password.isEmpty()) {
     boolean name = Datavalidation.textFieldIsNull(tf_username, label1, "Name is Required");
     boolean passw = Datavalidation.PasswordNull(pf_password, label2, "Password is Required");
 }else {
     ResultSet resultSet = statement.executeQuery("Select * from user where username='"+ username+"'and password='"+password+"'");
     if (!resultSet.next()) {
         infoBox("Enter Correct Email and Password", "Failed", null);
     }

 else {
     tf_username.getScene().getWindow().hide();
     Stage hello=new Stage();
     FXMLLoader loder=new FXMLLoader();
     loder.setLocation(getClass().getResource("/sample/views2/home.fxml"));
     loder.load();
     Parent root =loder.getRoot();
     Scene secene=new Scene(root);
     hello.getIcons().add(new Image("/sample/assests/image/finallogo.png"));
     hello.setTitle("home ");
     hello.setScene(secene);

     //set stage borderless
     hello.initStyle(StageStyle.UNDECORATED);

         //send name
         HomeController controller2 = loder.getController();
         controller2.setLabelText(username);

     hello.show();
 }

        }}



        public static void infoBox(String infoMessage, String titleBar, String headerMessage) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(headerMessage);
            alert.setContentText(infoMessage);
            alert.setTitle(titleBar);
            alert.showAndWait();
    }

    @FXML
   public void sinup(ActionEvent event) throws IOException {
//        Parent root= FXMLLoader.load(getClass().getResource("/sample/views/Sin_up.fxml"));
//        Node node=(Node) event.getSource();
//        Stage stage=(Stage) node.getScene().getWindow();
//        stage.setScene(new Scene(root));

        tf_username.getScene().getWindow().hide();
        Stage hello=new Stage();
        FXMLLoader loder=new FXMLLoader();
        loder.setLocation(getClass().getResource("/sample/views2/Sin_up.fxml"));
        loder.load();
        Parent root =loder.getRoot();
        Scene secene=new Scene(root);
        hello.getIcons().add(new Image("/sample/assests/image/finallogo.png"));
        hello.setTitle("Sing up ");
        hello.setScene(secene);

        //set stage borderless
        hello.initStyle(StageStyle.UNDECORATED);

        hello.show();
        }


        public void closeButtonAction(){
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.close();
        }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
