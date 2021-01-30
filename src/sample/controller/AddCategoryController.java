package sample.controller;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import sample.DataValidation.Datavalidation;
import sample.model.Category;

import java.sql.SQLException;

public class AddCategoryController {
    @FXML
    private Button closeButton;

    @FXML
    private Button add;

    @FXML
    private JFXTextField name_ca;

    @FXML
    private TextArea Description;
    @FXML
    private Label label1;

    @FXML
    private Label label2;



    //valid.setMessage("Please type something!");







    Category ca=new Category();

Stage stage;
    @FXML
    void add(ActionEvent event) {
          String name = name_ca.getText();
          String desc = Description.getText();
          if (name.isEmpty() && desc.isEmpty()){
              boolean namecategor= Datavalidation.textFieldIsNull(name_ca, label1, "name category is Required");
              //boolean namecategorie = Datavalidation.textAlphabet(name_ca, label1, "Please only enter letters from a - z");
              boolean descr=Datavalidation.textAriaIsNull(Description, label2, "description is Required");
          }else {
              String req = "insert into category values(NULL,'"+name+"','"+desc+"')";
              ca.exreq(req);
              stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
              stage.close();
          }
    }


    public void closeButtonAction(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }


}
