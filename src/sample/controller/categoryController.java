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
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.assests.db.Dbconnect;
import sample.model.Category;
import sample.model.Product;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.ResourceBundle;

public class categoryController  implements Initializable {

    @FXML
    private ComboBox<Category> nampr;
    ObservableList<Category> list1= FXCollections.observableArrayList();
    Dbconnect k = new Dbconnect();
    public void fillComboxCategory(){
        PreparedStatement ps;
        try {
            String req="select * from category";
            ps = k.getConnection().prepareStatement(req);
            ResultSet rs =ps.executeQuery();
            while (rs.next()){
                Category p =new Category();
                p.setIdcategory(rs.getInt("idcategory"));
                p.setNamecategory(rs.getString("namecategory"));
                list1.add(p);
            }
            nampr.setItems(list1);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    @FXML
    private Button addproduct;

    @FXML
    private TableView<Category> tab_info;

    @FXML
    private TableColumn<Category, Integer> col_id;

    @FXML
    private TableColumn<Category, String> col_namecategory;

    @FXML
    private TableColumn<Category, String> col_desc;

    @FXML
    private Button editproduct;
    Category o= new Category();

    @FXML
    void addcategory(ActionEvent event) {
        try
        {


            Stage hello=new Stage();
            FXMLLoader loder=new FXMLLoader();
            loder.setLocation(getClass().getResource("/sample/views2/category/addcategory.fxml"));
            loder.load();
            Parent root =loder.getRoot();
            Scene secene=new Scene(root);
            hello.getIcons().add(new Image("/sample/assests/image/finallogo.png"));
            hello.setTitle("Add Category");
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
        col_id.setCellValueFactory(new PropertyValueFactory<Category,Integer>("idcategory"));
        col_namecategory.setCellValueFactory(new PropertyValueFactory<Category,String>("namecategory"));
        col_desc.setCellValueFactory(new PropertyValueFactory<Category,String>("description"));
    }
    private  void loadData(){
        tab_info.setItems(o.show("select *from category"));
    }
    @FXML
    void refresh(ActionEvent event) {
        loadData();
        fillComboxCategory();
    }

    @FXML
    void delete(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Are you sure you want to delete this product?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Category category=(Category)tab_info.getSelectionModel().getSelectedItem();
            String  query="delete from category where idcategory="+category.getIdcategory()+"";
            o.exreq(query);
            loadData();
        }
    }

    @FXML
    void edit(ActionEvent event) {
        try
        {


            Stage hello=new Stage();
            FXMLLoader loder=new FXMLLoader();
            loder.setLocation(getClass().getResource("/sample/views2/category/editcategory.fxml"));
            loder.load();
            Parent root =loder.getRoot();
            Scene secene=new Scene(root);
            hello.getIcons().add(new Image("/sample/assests/image/finallogo.png"));
            hello.setTitle("Edit category");
            EditcategorieController cont=loder.getController();
            Category od=(Category) tab_info.getSelectionModel().getSelectedItem();
            cont.seTCategory(od);

            hello.setScene(secene);

            //set stage borderless
            hello.initStyle(StageStyle.UNDECORATED);

            hello.show();



        }catch (Exception e){
            System.out.println(e.getCause());
        }


    }



    @FXML
    void search(ActionEvent event) {

        Category category=(Category) tab_info.getSelectionModel().getSelectedItem();
        String  query="select *from category where namecategory='"+nampr.getValue()+"' ";
        tab_info.setItems(o.show(query));

    }
    @FXML
    void imprimer(ActionEvent event) {
        Stage stage = new Stage();
        PrinterJob printerJob = PrinterJob.createPrinterJob();
        if(printerJob.showPrintDialog(stage.getOwner()) && printerJob.printPage(tab_info))
            printerJob.endJob();
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initiaCols();
        loadData();
        fillComboxCategory();
        //nampr.setItems(list1);
    }
}
