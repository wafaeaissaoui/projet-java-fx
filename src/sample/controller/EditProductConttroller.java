package sample.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import sample.assests.db.Dbconnect;
import sample.model.Category;
import sample.model.Client;
import sample.model.Order;
import sample.model.Product;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class EditProductConttroller implements Initializable {
    Product product= new Product();

    @FXML
    private Button closeButton;

    @FXML
    private Button editproduct;

    @FXML
    private JFXTextField name_pr;

    @FXML
    private JFXTextField price;

    @FXML
    private JFXTextField quantity;

    @FXML
    private TextArea Description;
    @FXML
    private JFXTextField id;
    @FXML
    private JFXComboBox<Category> category;
    Dbconnect k=new Dbconnect();
    Stage stage;
    public void fillComboxCategory(){
        PreparedStatement ps;
        ObservableList<Category> c= FXCollections.observableArrayList();
        try {

            String req="select * from category ";
            ps = k.getConnection().prepareStatement(req);
            ResultSet rs =ps.executeQuery();
            while (rs.next()){
                Category ca =new Category();
                ca.setIdcategory(rs.getInt("idcategory"));
                ca.setNamecategory(rs.getString("namecategory"));
                c.add(ca);
            }
            category.setItems(c);
            category.getSelectionModel().select(0);
            //category.setDisable(true);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    @FXML
    void editproduct(ActionEvent event) {

        String name_pro =  name_pr.getText();
        int price_p= Integer.parseInt(price.getText());
        int quantity_p= Integer.parseInt(quantity.getText());
        Category c =new Category();
        c=category.getSelectionModel().getSelectedItem();
        //String  cat=category.getValue();
        String desc=Description.getText();
       // String re="update  product set  nameproduct='"+name_pro+"',price='"+price_p+"',qunatity='"+quantity_p+"',description='"+desc+"' where id_product='"+product.getId_product()+"'";
        product.updateProduct(Integer.parseInt(id.getText()),name_pro,price_p,quantity_p ,c.getIdcategory(),desc);
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();

    }

    public void setProduct(Product product){

        System.out.println(product.toString());
        id.setText(product.getId_product()+"");
        name_pr.setText(product.getNameproduct()+"");
        price.setText(product.getPrice()+"");
        quantity.setText(product.getQunatity()+"");
        fillComboxCategory();
        Description.setText(product.getDescription()+"");}


    public void closeButtonAction(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
