package sample.controller;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.DataValidation.Datavalidation;
import sample.assests.db.Dbconnect;
import sample.model.Category;
import sample.model.Client;
import sample.model.Order;
import sample.model.Product;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class AddProductController implements Initializable {
    @FXML
    private Button closeButton;

    @FXML
    private Button addproduct;

    @FXML
    private JFXTextField name_product;

    @FXML
    private JFXTextField price;

    @FXML
    private JFXTextField quntity;

    @FXML
    private JFXComboBox<Category> category;
    ObservableList<Category> category1= FXCollections.observableArrayList();

    @FXML
    private TextArea description;
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
    Stage stage;
    @FXML
    private RequiredFieldValidator requiredFieldValidator;

    //@FXML
    //private RequiredFieldValidator requiredFieldValidator1;

    //@FXML
    //private RequiredFieldValidator requiredFieldValidator2;


    // private void setupValidation() {
    //        requiredFieldValidator = new RequiredFieldValidator();
    //        requiredFieldValidator.setIcon(new ImageView(getClass().getResource("/assests/images/err.png").toString()));
    //        requiredFieldValidator.setMessage("champs obligatoire");
    //
    //        name_product.getValidators().add(requiredFieldValidator);
    //        name_product.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
    //            if (!newValue) {
    //                name_product.validate();
    //            }
    //


        Dbconnect k = new Dbconnect();
    Product o =new Product();

    public void fillComboxCategory(){
        PreparedStatement ps;
        try {
            String req="select * from category ";
            ps = k.getConnection().prepareStatement(req);
            ResultSet rs =ps.executeQuery();
            while (rs.next()){
                Category c =new Category();
                c.setIdcategory(rs.getInt("idcategory"));
                c.setNamecategory(rs.getString("namecategory"));
                category1.add(c);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    @FXML
    void addproduct(ActionEvent event) {
        boolean name= Datavalidation.textFieldIsNull(name_product, label1, "name product is Required");
        boolean qnt=Datavalidation.textFieldIsNull(quntity, label3, "quantity is Required");
        boolean pric=Datavalidation.textFieldIsNull(price, label2, "price is Required");
        boolean namecl= Datavalidation.ComboxdNull(category, label4, "category is Required");
        boolean sat=Datavalidation.textAriaIsNull(description, label5, "description is Required");

        String name_pro = name_product.getText();
        int price_p= Integer.parseInt(price.getText());
        int quantity_p= Integer.parseInt(quntity.getText());
        Category c =new Category();
        c=category.getSelectionModel().getSelectedItem();
        String descri = description.getText();

        String req="insert into product values(NUll,'"+c.getIdcategory()+"','"+name_pro+"','"+price_p+"','"+quantity_p+"','"+descri+"')";
        o.exreq(req);
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void closeButtonAction(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillComboxCategory();
        //setupValidation();
        //requiredFieldValidator = new RequiredFieldValidator();
        //requiredFieldValidator.setMessage("champs obligatoire");
        //name_product.getValidators().add(requiredFieldValidator);
        //        name_product.focusedProperty().addListener(new ChangeListener<Boolean>() {
        //                                                       @Override
        //                                                       public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
        //                                                           if (!newValue) {
        //                                                               name_product.validate();
        //                                                           }
        //                                                       }
        //                                                   });

        category.setItems(category1);
    }
}

