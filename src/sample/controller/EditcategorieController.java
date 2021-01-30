package sample.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import sample.model.Category;

public class EditcategorieController {
    @FXML
    private Button closeButton;

    @FXML
    private Button edit;

    @FXML
    private JFXTextField name_ca;
    @FXML
    private JFXTextField id;

    @FXML
    private TextArea Description;
    Stage stage;
    Category Ca =new Category();
    public  void seTCategory(Category Ca){
        id.setText(Ca.getIdcategory()+"");
        name_ca.setText(Ca.getNamecategory()+"");
        Description.setText(Ca.getDescription()+"");
    }

    public void closeButtonAction(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void edit(ActionEvent event) {
        String name=name_ca.getText();
        String desc=Description.getText();
        //String req="update  category set namecategory='"+name+"',comment='"+desc+"' ";
        Ca.updatecategory(Integer.parseInt(id.getText()),name_ca.getText(),Description.getText());
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }

}
