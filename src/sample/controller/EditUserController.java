package sample.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import sample.model.Category;
import sample.model.Users;

public class EditUserController {
    @FXML
    private JFXTextField firstName;

    @FXML
    private Button edit;

    @FXML
    private Button closeButton;

    @FXML
    private JFXTextField familyName;

    @FXML
    private JFXPasswordField password2;

    @FXML
    private JFXTextField id;

    @FXML
    private JFXPasswordField password1;

    @FXML
    private JFXTextField userName;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXPasswordField newPassword;

    @FXML
    private Label label1;

    @FXML
    private Label label2;

    @FXML
    private Label currentPassword;

    Stage stage;
    Users Us = new Users();
    public  void setUser(Users Us){
        id.setText(Us.getId() + "");
        userName.setText(Us.getUserName() + "");
        familyName.setText(Us.getLastName() + "");
        firstName.setText(Us.getFirstName() + "");
        email.setText(Us.getEmail() + "");
        password1.setText(Us.getPassword() + "");
        password2.setText(Us.getPassword() + "");
        currentPassword.setText(Us.getPassword());
    }

    public void closeButtonAction(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void edit(ActionEvent event) {
        if(!password1.getText().matches(password2.getText())) {
            label2.setText("Les mots de passe ne correspondent pas!");
        }
        if(!currentPassword.getText().matches(newPassword.getText())) {
            label1.setText("Mot de passe incorrect!");
        }
        if(currentPassword.getText().matches(newPassword.getText()) && !newPassword.getText().isEmpty() && password1.getText().matches(password2.getText())) {
            label1.setText("");
            label2.setText("");
            Us.updateUser(Integer.parseInt(id.getText()), userName.getText(), familyName.getText(), firstName.getText(), email.getText(), password1.getText());
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.close();
        }
    }

}
