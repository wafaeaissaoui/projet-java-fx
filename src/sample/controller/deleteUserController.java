package sample.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample.model.Users;

public class deleteUserController {
    @FXML
    private JFXTextField id;

    @FXML
    private JFXPasswordField password;

    @FXML
    private Label label1;

    @FXML
    private Label currentPassword;

    @FXML
    private Button closeButton;

    Stage stage;
    Users Us = new Users();
    public void setPassUser(Users Us){
        id.setText(Us.getId() + "");
        currentPassword.setText(Us.getPassword());
    }


    public void closeButtonAction(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }


    @FXML
    void delete(ActionEvent event) {
        if(!currentPassword.getText().matches(password.getText())) {
            label1.setText("Mot de passe incorrect!");
        }
        if(currentPassword.getText().matches(password.getText()) && !password.getText().isEmpty()) {
            label1.setText("");
            String  query="delete from user where id = " + Integer.parseInt(id.getText()) + "";
            Us.exreq(query);
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.close();
        }
    }



}
