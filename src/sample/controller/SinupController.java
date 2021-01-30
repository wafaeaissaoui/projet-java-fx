package sample.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.StageStyle;
import sample.DataValidation.Datavalidation;
import sample.assests.db.Dbconnect;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class SinupController implements Initializable {
    @FXML
    private Button closeButton;

    @FXML
    private Label label4;

    @FXML
    private Label label5;

    @FXML
    private Label label3;

    @FXML
    private Label label2;

    @FXML
    private Label label1;
    @FXML
    private JFXTextField tf_username;

    @FXML
    private JFXTextField tf_email;

    @FXML
    private PasswordField pf_password;


    @FXML
    private JFXTextField tf_Lname;

    @FXML
    private JFXTextField tf_Fname;



    @FXML
    void login(ActionEvent event) throws IOException {


        tf_username.getScene().getWindow().hide();
        Stage hello=new Stage();
        FXMLLoader loder=new FXMLLoader();
        loder.setLocation(getClass().getResource("/sample/views2/login.fxml"));
        loder.load();
        Parent root =loder.getRoot();
        Scene secene=new Scene(root);
        hello.setTitle("Hello World");
        hello.setScene(secene);

        //set stage borderless
        hello.initStyle(StageStyle.UNDECORATED);

        hello.show();
    }

    @FXML
    void sinup(ActionEvent event) {
        Connection connection= Dbconnect.getInstance().getConnection();
        boolean name= Datavalidation.textFieldIsNull(tf_username, label1, "UserName is Required");
        boolean emaivaidation= Datavalidation.textFieldIsNull(tf_email, label2, "Email is Required");
        boolean password= Datavalidation.PasswordNull(pf_password, label3, "password is Required");
        boolean nomValidation= Datavalidation.textFieldIsNull(tf_Lname, label4, "LastName is Required");
        boolean prenomValidation= Datavalidation.textFieldIsNull(tf_Fname, label5, "FirstName is Required");
        //
        try {
            boolean emailvaliformat=Datavalidation.emailFormat(tf_email, label2, "Format must be name@emailaddress.com");
            boolean passwordvalide=Datavalidation.dataLength(pf_password, label3, "Must be 8 characters", "2");
            String unsername= tf_username.getText();
            String email=tf_email.getText();
            String pass=pf_password.getText();
            String nom=tf_Lname.getText();
            String prenom=tf_Fname.getText();
            Statement statement= connection.createStatement();
            if (emailvaliformat && passwordvalide){
                int status= statement.executeUpdate("insert into user (username,familyName,firstName,email,password) values('"+unsername+"','" + nom + "','" + prenom + "','"+email+"','"+pass+"')");

            }
//            if (status>0){
//                System.out.println("user registered");
//            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeButtonAction(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
