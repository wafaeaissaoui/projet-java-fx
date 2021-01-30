package sample.controller;

import com.jfoenix.controls.JFXTextField;
import com.sun.org.apache.xpath.internal.operations.Or;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Main;
import sample.assests.db.Dbconnect;
import sample.model.Order;
import sample.model.Product;

import javax.swing.*;
import java.net.URL;
import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;

//import sample.model.Order.getProduct;

public class OrderController implements Initializable {


    @FXML
    private TableView<Order> tab_info;

    @FXML
    private TableColumn<Order, Integer> col_id;

    @FXML
    private TableColumn<Order, String>col_nameclient;

    @FXML
    private TableColumn<Order, String>col_product;

    @FXML
    private TableColumn<Order, Integer> col_number;

    @FXML
    private TableColumn<Order, Integer> col_price;

    @FXML
    private TableColumn<Order, String>col_statut;
    @FXML
    private TableColumn<Order, String> col_date;

    @FXML
    private TableColumn<Order, Integer> col_total;
    @FXML
    private Button addOrder;
    @FXML
    private Button Editorder;
    @FXML
    private Button deleteOrder;
    @FXML
    private JFXTextField nameclient;

    @FXML
    private ComboBox<Product> product;
    ObservableList<Product> prod= FXCollections.observableArrayList();
    Order o =new Order();
    @FXML
    private ComboBox<String> status;
    ObservableList<String> list= FXCollections.observableArrayList("livree","en cours");

    @FXML
    private Button searchOrder;
    ObservableList<Order> tab_data= FXCollections.observableArrayList();
    Dbconnect k = new Dbconnect();
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
                prod.add(p);
            }
            product.setItems(prod);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    @FXML
    void Editorder(ActionEvent event) {
        try
        {
            Order order=(Order)tab_info.getSelectionModel().getSelectedItem();


            Stage hello=new Stage();
            FXMLLoader loder=new FXMLLoader();
            loder.setLocation(getClass().getResource("/sample/views2/order/Edit_order.fxml"));
            loder.load();
            Parent root =loder.getRoot();
            Scene secene=new Scene(root);
            hello.setTitle("Edit order");
            EditorderController cont=loder.getController();
            Order od=(Order) tab_info.getSelectionModel().getSelectedItem();
            cont.setOrder(od);
            hello.getIcons().add(new Image("/sample/assests/image/finallogo.png"));
            hello.setScene(secene);

            //set stage borderless
            hello.initStyle(StageStyle.UNDECORATED);

            hello.show();



        }catch (Exception e){
            System.out.println(e.getCause());
        }}



    @FXML
   public void addOrder(ActionEvent event) {
try
{


    Stage hello=new Stage();
    FXMLLoader loder=new FXMLLoader();
    loder.setLocation(getClass().getResource("/sample/views2/order/new_order.fxml"));
    loder.load();
    Parent root =loder.getRoot();
    Scene secene=new Scene(root);
    hello.getIcons().add(new Image("/sample/assests/image/finallogo.png"));
    hello.setTitle("Add Order");
    hello.setScene(secene);

    //set stage borderless
    hello.initStyle(StageStyle.UNDECORATED);

    hello.show();



}catch (Exception e){
    System.out.println(e.getCause());
}}





    private  void initiaCols(){
        //id_o,nam_client,product,number,price,status
        col_id.setCellValueFactory(new PropertyValueFactory<Order,Integer>("id_o"));
        col_nameclient.setCellValueFactory(new PropertyValueFactory<Order,String>("nam_client"));
        col_product.setCellValueFactory(new PropertyValueFactory<Order,String>("product"));
        col_number.setCellValueFactory(new PropertyValueFactory<Order,Integer>("number"));
        col_price.setCellValueFactory(new PropertyValueFactory<Order,Integer>("price"));
        col_statut.setCellValueFactory(new PropertyValueFactory<Order,String>("status"));
        col_date.setCellValueFactory(new PropertyValueFactory<Order,String>("date"));
        col_total.setCellValueFactory(new PropertyValueFactory<Order,Integer>("total"));
    }

    private  void loadData(){

        tab_info.setItems(o.show("select id,idclient ,idproduct,number,date,prix,status,nameclient,nameproduct,number*prix from orde left join product on id_product=idproduct left join client on idclient =id_client"));
    }
    @FXML
    void deleteOrder(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Are you sure you want to delete this Order?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Order order =(Order)tab_info.getSelectionModel().getSelectedItem();
            String  query="delete from orde where id="+order.getId_o()+"";
            o.exreq(query);
            loadData();

        } else {
        }
    }

    @FXML
    void searchOrder(ActionEvent event) {
        Order order=(Order)tab_info.getSelectionModel().getSelectedItem();
        String  query="select id,idclient ,idproduct,number,date,prix,status,nameclient,nameproduct,number*prix from orde left join product on id_product=idproduct left join client on id_client=idclient  where client.nameclient = '"+nameclient.getText()+"' AND product.nameproduct= '"+product.getValue()+"' AND status= '"+status.getValue()+"'";
        tab_info.setItems(o.show(query));

    }
    @FXML
    void refresh(ActionEvent event) {
        //fillComboxProduct();
        loadData();
    }

    @FXML
    void livree(ActionEvent event) {
        Stage stage = new Stage();
        PrinterJob printerJob = PrinterJob.createPrinterJob();
        if(printerJob.showPrintDialog(stage.getOwner()) && printerJob.printPage(tab_info))
            printerJob.endJob();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initiaCols();
        loadData();
        status.setItems(list);
        //product.setItems(list1);
        fillComboxProduct();
    }



}
