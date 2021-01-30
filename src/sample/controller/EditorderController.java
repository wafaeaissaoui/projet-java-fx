package sample.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import sample.assests.db.Dbconnect;
import sample.model.Category;
import sample.model.Client;
import sample.model.Order;
import sample.model.Product;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;



public class EditorderController implements Initializable {
    Order or =new Order();
    Dbconnect k=new Dbconnect();
    public void fillComboxClient(){
        PreparedStatement ps;
        ObservableList<Client> client= FXCollections.observableArrayList();
        try {

            String req="select * from client";
            ps = k.getConnection().prepareStatement(req);
            ResultSet rs =ps.executeQuery();
            while (rs.next()){
                Client c =new Client();
                c.setId(rs.getInt("id_client"));
                c.setNameclient(rs.getString("nameclient"));
                client.add(c);
            }
            name_client.setItems(client);
            name_client.getSelectionModel().select(0);
            name_client.setDisable(true);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    public void fillComboxProduct(){
        PreparedStatement ps;
        ObservableList<Product> product= FXCollections.observableArrayList();
        try {
            String req="select * from product ";
            ps = k.getConnection().prepareStatement(req);
            ResultSet rs =ps.executeQuery();
            while (rs.next()){
                Product p =new Product();
                p.setId_product(rs.getInt("id_product"));
                p.setNameproduct(rs.getString("nameproduct"));
                product.add(p);
            }
            name_product.setItems(product);
            name_product.getSelectionModel().select(0);
            name_product.setDisable(true);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }


   public void setOrder(Order o){
         or=o;
         fillComboxClient();
         fillComboxProduct();
       System.out.println(or.toString());
       status.setText(or.getStatus()+"");
       quality.setText(or.getNumber()+"");
       price.setText(or.getPrice()+"");
       price.setDisable(true);
       date.setText(or.getDate());
        String req="select  * from orde where id="+or.getId_o()+"";
        //name_product.setItems(or.show(req));
        //name_client.setValue(or.getNam_client());
        //name_product.setValue(or.getProduct());
   }

    @FXML
    private Button closeButton;

    @FXML
    private ComboBox<Client> name_client;

    @FXML
    private ComboBox<Product> name_product;
    @FXML
    private JFXTextField status;

    @FXML
    private JFXTextField quality;

    @FXML
    private JFXTextField price;

    @FXML
    private JFXTextField  date;

    @FXML
    private JFXTextField total;


    Stage stage;


    @FXML
    private Button EditOrder;
    private
    TableView<Order> tab_info;
  Connection connection= Dbconnect.getInstance().getConnection();

    public void Showonclick(){
    try {

        String  query="select *from `order` where id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        //preparedStatement.setInt(1, Order.getId_o());
        ResultSet resultSet=preparedStatement.executeQuery();

        //name_client.setValue(resultSet.getString("nam_client"));
       //name_product.setValue(resultSet.getString("product"));
        quality.setText(String.valueOf(resultSet.getInt("number")));
        price.setText(String.valueOf(resultSet.getInt("price")));
        status.setText(resultSet.getString("status"));
        date.setAccessibleText(String.valueOf(resultSet.getString("date")));



    }catch (Exception e){

    }

    }


    public void EditOrder(ActionEvent event) {
        System.out.println("test");
       Client c = new Client();
        //c=;
        Product p= new Product();
        p=name_product.getSelectionModel().getSelectedItem();

        try {
            String  query="update  orde set  number=?,date=?,prix=?,status=?  where id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(5,or.getId_o());
            //preparedStatement.setInt(1,name_client.getSelectionModel().getSelectedItem());
            //preparedStatement.setInt(2,p);
            preparedStatement.setInt(1,Integer.parseInt(quality.getText().trim()));
            preparedStatement.setString(2,date.getText());
            preparedStatement.setInt(3,Integer.parseInt(price.getText().trim()));
            preparedStatement.setString(4,status.getText());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }


    }
    @FXML
    void annuler(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
    public void closeButtonAction(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
