package sample.controller;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample.DataValidation.Datavalidation;
import sample.assests.db.Dbconnect;
import sample.model.Client;
import sample.model.Order;
import sample.model.Product;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class AddorderController  implements Initializable {
    @FXML
    private Button closeButton;

    @FXML
    private ComboBox<Client> name_client;
    ObservableList<Client> client= FXCollections.observableArrayList();

    @FXML
    private ComboBox<Product> name_product;
    ObservableList<Product> product= FXCollections.observableArrayList();
    @FXML
    private JFXTextField status;

    @FXML
    private JFXTextField quality;

    @FXML
    private JFXTextField price;
    //@FXML
    //private JFXDatePicker date;

    @FXML
    private DatePicker date;

    @FXML
    private Button Addorder;
    @FXML
    private Label label1;

    @FXML
    private Label labell2;

    @FXML
    private Label label3;

    @FXML
    private Label label4;

    @FXML
    private Label label5;
    @FXML
    private Label label6;
    Stage stage;
    Dbconnect k = new Dbconnect();
    Order o =new Order();
    @FXML
    void Addorder(ActionEvent event) {
//        Connection connection= Dbconnect.getInstance().getConnection();
//        try {
//            String name_client = nam_client.getText();
//            String name_pro = name_product.getText();
//            int quantity= Integer.parseInt(quality.getText());
//            int price_or= Integer.parseInt(price.getText());
//            String status_order=status.getText();
//
//            Statement statement= connection.createStatement();
//             statement.executeUpdate("insert into `order`(nam_client,product,number,price,status) values('"+name_client +"','"+name_pro+"',"+quantity+","+price_or+",'"+ status_order+"')");
//          //  int status= statement.executeUpdate("insert into `order`(nam_client,product,number,price,status) values('"+name_client +"','"+name_pro+"',"+quantity+","+price_or+",'"+ status_order+"')");
//
//
////            if (status){
////                System.out.println("order registered");
////            }
//
//        } catch (SQLException e) {
//           // e.printStackTrace();
//            System.out.println(e.toString());
//        }
////////////////////////////////////////-----------------------------------------
        //nv code

//             String name_cl = (String) name_client.getValue();
//            String name_pro = (String) name_product.getValue();

        //!-------------------------------------------

        boolean name= Datavalidation.ComboxdNull(name_product, label1, "name product is Required");
        boolean namecl= Datavalidation.ComboxdNull(name_client, labell2, "name client is Required");
        boolean qnt=Datavalidation.textFieldIsNull(quality, label3, "quantity is Required");
        boolean pric=Datavalidation.textFieldIsNull(price, label4, "price is Required");
        boolean sat=Datavalidation.textFieldIsNull(status, label5, "status is Required");
        //boolean da=Datavalidation.textFieldIsNull(date, label5, "status is Required");

        Product p =new Product();
        p=name_product.getSelectionModel().getSelectedItem();
        Client c =new Client();
        c=name_client.getSelectionModel().getSelectedItem();
            int quantity= Integer.parseInt(quality.getText());
            int price_or= Integer.parseInt(price.getText());
            String status_order=status.getText();
          // int  date1=date.getInt();
        LocalDate datee=date.getValue();
        //String re="insert into orde values(NUll,'"+name_cl +"','"+name_pro+"',"+quantity+","+price_or+",'"+ status_order+"')";
    String re="insert into orde values(NUll,"+c.getId_client()+","+p.getId_product()+","+quantity+",'"+datee+"',"+price_or+",'"+ status_order+"')";
    o.exreq(re);
        //!-----------------------------------------------

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();


    }
    public void fillComboxClient(){
        PreparedStatement ps;
        try {
            String req="select * from client ";
            ps = k.getConnection().prepareStatement(req);
           ResultSet rs =ps.executeQuery();
            while (rs.next()){
                Client c =new Client();
                c.setId(rs.getInt("id_client"));
                c.setNameclient(rs.getString("nameclient"));
             client.add(c);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    public void fillComboxProduct(){
        PreparedStatement ps;
        try {
            String req="select * from product";
            ps = k.getConnection().prepareStatement(req);
            ResultSet rs =ps.executeQuery();
            while (rs.next()){
                Product p =new Product();
                p.setId_product(rs.getInt("id_product"));
                p.setNameproduct(rs.getString("nameproduct"));
                product.add(p);
            }
            name_product.setItems(product);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void closeButtonAction(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillComboxClient();
        name_client.setItems(client);
        fillComboxProduct();

        name_product.valueProperty().addListener(new ChangeListener<Product>() {
            @Override
            public void changed(ObservableValue<? extends Product> observable, Product oldValue, Product newValue) {
             // price.setText(newValue.getPrice()+"");
                System.out.println(newValue.id_product);
                Product p =new Product();

               // usersl.forEach((tab) -> {
//             System.out.println("Stuff with "+usersl);
//         });
               // belati modifier raki te9edi diri ri hadi bela matezdi dik show2
              // p.show("select id,idclient ,idproduct,number,date,prix,status,nameclient,nameproduct from orde inner join product on id_product=idproduct inner join client on idclient =id_client where id_product="+newValue.id_product));
                p.show2("select * from product where id_product="+newValue.id_product).forEach((tab) -> {
                    Product p1=new Product();
                    p1.setPrice(tab.getPrice());
                    price.setText(p1.getPrice()+"");
                    //System.out.println(p1.getPrice());
                }
                );
            }
        });

//        name_product.setItems(product);
     //test pour del order
        // String re="DELETE from`order` where id=23";
        //        o.exreq(re);

    }
}
