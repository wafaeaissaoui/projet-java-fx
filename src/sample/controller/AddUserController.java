package sample.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample.DataValidation.Datavalidation;
import sample.model.Users;

public class AddUserController {

    @FXML
    private Button add;

    @FXML
    private Label label1;

    @FXML
    private Label label2;

    @FXML
    private Label label3;

    @FXML
    private Label label4;

    @FXML
    private Label label5;

    @FXML
    private Label label6;

    @FXML
    private Label label7;

    @FXML
    private Button closeButton;

    @FXML
    private JFXTextField userName;

    @FXML
    private JFXTextField familyName;

    @FXML
    private JFXTextField firstName;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXPasswordField password1;

    @FXML
    private JFXPasswordField password2;




    Users us = new Users();

    Stage stage;
    @FXML
    void add(ActionEvent event) {
        String vUserName = userName.getText();
        String vFamilyName = familyName.getText();
        String vFirstName = firstName.getText();
        String vEmail = email.getText();
        String vPassword1 = password1.getText();
        String vPassword2 = password2.getText();

        boolean userNameValidation = Datavalidation.textFieldIsNull(userName, label1, "Entrer un nom d'utilisateur!");
        boolean familyNameValidation = Datavalidation.textFieldIsNull(familyName, label2, "Entrer un nom!");
        boolean firstNameValidation = Datavalidation.textFieldIsNull(firstName, label3, "Entrer un prénom!");
        boolean emailValidation = Datavalidation.textFieldIsNull(email, label4, "Entrer un e-mail!");
        boolean password1Validation = Datavalidation.PasswordNull(password1, label5, "Entrer un mot de passe!");
        boolean password2Validation = Datavalidation.PasswordNull(password2, label6, "Confirmez votre mot de passe!");
        if(!vPassword1.matches(vPassword2)){ label7.setText("Les mots de passe ne correspondent pas!"); } else{ label7.setText(""); }

        if(!vUserName.isEmpty() && !vFamilyName.isEmpty() && !vFirstName.isEmpty() && !vEmail.isEmpty() && !vPassword1.isEmpty() && !vPassword2.isEmpty() && vPassword1.matches(vPassword2)){
            String req = "insert into user values(NULL, '" + vUserName + "', '" + vFamilyName + "', '" + vFirstName + "', '" + vEmail + "', '" + vPassword1 + "')";
            us.exreq(req);
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.close();
        }
    }


    public void closeButtonAction(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

}
