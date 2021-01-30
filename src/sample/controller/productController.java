package sample.controller;

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
import sample.assests.db.Dbconnect;
import sample.model.Category;
import sample.model.Order;
import sample.model.Product;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.ResourceBundle;

public class productController implements Initializable {
    @FXML
    private Button addproduct;
    @FXML
    private Button editproduct;
    @FXML
    private ComboBox<Product> nampr;
    ObservableList<Product> list1= FXCollections.observableArrayList();
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
                list1.add(p);
            }
            nampr.setItems(list1);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    @FXML
    private TableView<Product> tab_info;

    @FXML
    private TableColumn<Product, Integer> col_id;

    @FXML
    private TableColumn<Product, String> col_nameproduct;

    @FXML
    private TableColumn<Product, Integer> col_price;

    @FXML
    private TableColumn<Product, Integer> col_quantity;

    @FXML
    private TableColumn<Product, String>  col_category;

    @FXML
    private TableColumn<Product, String> col_descreption;
    Product o =new Product();

    @FXML
    void addproduct(ActionEvent event) {
        try
        {


            Stage hello=new Stage();
            FXMLLoader loder=new FXMLLoader();
            loder.setLocation(getClass().getResource("/sample/views2/product/new_product1.fxml"));
            loder.load();
            Parent root =loder.getRoot();
            Scene secene=new Scene(root);
            hello.getIcons().add(new Image("/sample/assests/image/finallogo.png"));
            hello.setTitle("Add Product");
            hello.setScene(secene);

            //set stage borderless
            hello.initStyle(StageStyle.UNDECORATED);

            hello.show();



        }catch (Exception e){
            System.out.println(e.getCause());
        }}
    @FXML
    void editproduct(ActionEvent event) {
        try
        {


            Stage hello=new Stage();
            FXMLLoader loder=new FXMLLoader();
            loder.setLocation(getClass().getResource("/sample/views2/product/Edit_product.fxml"));
            loder.load();
            Parent root =loder.getRoot();
            Scene secene=new Scene(root);
            hello.setTitle("Edit Product");
            EditProductConttroller cont=loder.getController();
            Product od=(Product) tab_info.getSelectionModel().getSelectedItem();
            cont.setProduct(od);
            hello.getIcons().add(new Image("/sample/assests/image/finallogo.png"));
            hello.setScene(secene);

            //set stage borderless
            hello.initStyle(StageStyle.UNDECORATED);

            hello.show();



        }catch (Exception e){
            System.out.println(e.getCause());
        }

    }

    private  void initiaCols(){
        //id,nameproduct,price,quntity,category,descreption
        col_id.setCellValueFactory(new PropertyValueFactory<Product,Integer>("id_product"));
        col_nameproduct.setCellValueFactory(new PropertyValueFactory<Product,String>("nameproduct"));
        col_price.setCellValueFactory(new PropertyValueFactory<Product, Integer>("price"));
        col_quantity.setCellValueFactory(new PropertyValueFactory<Product,Integer>("qunatity"));
        col_category.setCellValueFactory(new PropertyValueFactory<Product,String>("category"));
        col_descreption.setCellValueFactory(new PropertyValueFactory<Product,String>("description"));

    }

    private  void loadData(){

//        String  query="select *from orde inner join product on id_product=idproject inner join client on id_client=idcilent  where client.nameclient ='"+nameclient.getText()+"'or product.nameproduct='"+product.getValue()+"' AND status='"+status.getValue()+"'";
        tab_info.setItems(o.show("select  product.id_product,product.id_category, product.nameproduct, product.price,product.qunatity,product.description,category.namecategory from product left join category on category.idcategory=product.id_category ;"));
    }
    @FXML
    void searchProduct(ActionEvent event) {
        Product product=(Product) tab_info.getSelectionModel().getSelectedItem();
        String  query="select  id_product,id_category, nameproduct, price,qunatity,description,namecategory from product inner join category on idcategory=id_category where nameproduct='"+nampr.getValue()+"' ";
        tab_info.setItems(o.show(query));
    }
    @FXML
    void deleteproduct(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Are you sure you want to delete this product?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
           Product product=(Product)tab_info.getSelectionModel().getSelectedItem();
           String  query="delete from product where id_product="+product.getId_product()+"";
            o.exreq(query);
            loadData();
        }
    }
    @FXML
    void refresh(ActionEvent event) {
        loadData();
        fillComboxProduct();
    }
    @FXML
    void imprime(ActionEvent event) {
        Stage stage = new Stage();
        PrinterJob printerJob = PrinterJob.createPrinterJob();
        if(printerJob.showPrintDialog(stage.getOwner()) && printerJob.printPage(tab_info))
            printerJob.endJob();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initiaCols();
        loadData();
        //nampr.setItems(list1);
        fillComboxProduct();

    }
}
