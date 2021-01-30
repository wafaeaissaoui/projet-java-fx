package sample.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample.DataValidation.Datavalidation;
import sample.model.Client;

import java.net.URL;
import java.util.ResourceBundle;

public class AddClientController implements Initializable {
    @FXML
    private Button closeButton;

    @FXML
    private JFXTextField tele;

    @FXML
    private JFXTextField namecl;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXTextField total_delivred;

    @FXML
    private JFXTextField total_canceled;

   // private EmailValidator emailValidator;

    @FXML
    private JFXComboBox<String> sexe;
    ObservableList<String> list= FXCollections.observableArrayList("femme","homme");
    Client client=new Client();
    @FXML
    private Label label1;
    @FXML
    private Label label2;

    @FXML
    private Label label3;

    @FXML
    private Label labell4;

    @FXML
    private Label label5;

    @FXML
    private Label label6;
    Stage stage;
    @FXML
    void addclient(ActionEvent event) {
        boolean namclit= Datavalidation.textFieldIsNull(namecl, label2, "Name is Required");
        boolean nameclk=Datavalidation.textFieldIsNull(email, label3, "Email is Required");
        boolean sex=Datavalidation.ComboxdNull(sexe, labell4, "Sexe is Required");
        boolean tel=Datavalidation.textFieldIsNull(tele, label1, "Telephone is Required");
        boolean tol=Datavalidation.textFieldIsNull(total_delivred, label5, "total delivred is Required");
        boolean tol1=Datavalidation.textFieldIsNull(total_canceled, label6, "total canceled is Required");
        boolean emailValidation = Datavalidation.emailFormat(email, label3, "Format must be name@emailaddress.com");
        boolean numericPhNumber = Datavalidation.textNumeric(tele, label1, "Please only enter number phone ");
       boolean Number = Datavalidation.textNumeric(total_delivred, label5, "Please only enter numbers from 0 - 9");
       boolean PhNumber = Datavalidation.textNumeric(total_canceled, label6, "Please only enter numbers from 0 - 9");
     String nameclient;
        nameclient = namecl.getText();
        String emailcl=email.getText();
     String telephone=tele.getText();
     String sexecl=sexe.getValue();
     int totaorddel= Integer.parseInt(total_delivred.getText());
     int totaordcan= Integer.parseInt(total_canceled.getText());
if (nameclient.isEmpty() && emailcl.isEmpty() && sexecl.isEmpty() && telephone.isEmpty()){

}else if(emailValidation && numericPhNumber && Number && PhNumber){
        String req="insert into client values(NUll,'"+nameclient+"','"+telephone+"','"+emailcl+"','"+sexecl+"','"+Integer.parseInt(total_delivred.getText())+"','"+Integer.parseInt(total_canceled.getText())+"') ";
        client.exreq(req);
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.close();

}


    }

    public void closeButtonAction(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sexe.setItems(list);

    }
}
