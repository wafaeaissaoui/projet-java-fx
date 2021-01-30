package sample.controller;

import com.jfoenix.controls.JFXTextField;
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
import sample.model.Client;
import sample.model.Product;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class clientController implements Initializable {

    @FXML
    private Button searchclient;

    @FXML
    private Button addClient;

    @FXML
    private TableView<Client> tab_info;

    @FXML
    private TableColumn<Client, Integer> col_id;

    @FXML
    private TableColumn<Client, String>col_nameclient;

    @FXML
    private TableColumn<Client, String> col_tele;
    @FXML
    private TableColumn<Client, String> col_email;

    @FXML
    private TableColumn<Client, String> col_orderdelivred;

    @FXML
    private TableColumn<Client, String>col_ordercancelled;

    @FXML
    private TableColumn<Client, String> col_price;
    @FXML
    private TableColumn<Client, Integer> col_total;
    @FXML
    private TableColumn<Client, Integer> col_total1;
    @FXML
    private JFXTextField txtserch;
    Client client=new Client();

    @FXML
    void addClient(ActionEvent event) {
        try
        {


            Stage hello=new Stage();
            FXMLLoader loder=new FXMLLoader();
            loder.setLocation(getClass().getResource("/sample/views2/client/Addclient.fxml"));
            loder.load();
            Parent root =loder.getRoot();
            Scene secene=new Scene(root);
            hello.getIcons().add(new Image("/sample/assests/image/finallogo.png"));
            hello.setTitle("Add client");
            hello.setScene(secene);

            //set stage borderless
            hello.initStyle(StageStyle.UNDECORATED);

            hello.show();



        }catch (Exception e){
            System.out.println(e.getCause());
        }}

    @FXML
    void editClient(ActionEvent event) {
        try
        {


            Stage hello=new Stage();
            FXMLLoader loder=new FXMLLoader();
            loder.setLocation(getClass().getResource("/sample/views2/client/editclient.fxml"));
            loder.load();
            Parent root =loder.getRoot();
            Scene secene=new Scene(root);
            EditClientController cont=loder.getController();
            Client od=(Client) tab_info.getSelectionModel().getSelectedItem();
            cont.setclient(od);
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
        //name_client,tele,email,order_delivred,order_cancelled,price
        col_id.setCellValueFactory(new PropertyValueFactory<Client,Integer>("id_client"));
        col_nameclient.setCellValueFactory(new PropertyValueFactory<Client,String>("nameclient"));
        col_tele.setCellValueFactory(new PropertyValueFactory<Client,String>("tele"));
        col_email.setCellValueFactory(new PropertyValueFactory<Client,String>("email"));
        col_price.setCellValueFactory(new PropertyValueFactory<Client,String>("sexe"));
        col_total.setCellValueFactory(new PropertyValueFactory<Client,Integer>("order_delevrd"));
        col_total1.setCellValueFactory(new PropertyValueFactory<Client,Integer>("order_cancelled"));
    }

    private  void loadData(){

        tab_info.setItems(client.show("select * from client"));
    }
    @FXML
    void refresh(ActionEvent event) {
        loadData();
    }

    @FXML
    void search(ActionEvent event) {
        Client clie=(Client) tab_info.getSelectionModel().getSelectedItem();
            String Rq="select * from client where nameclient='"+txtserch.getText()+"' or email='"+txtserch.getText()+"' or tele='"+txtserch.getText()+"'";
            tab_info.setItems(client.show(Rq));
    }

    @FXML
    void deleteclient(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Are you sure you want to delete this client?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Client client=(Client) tab_info.getSelectionModel().getSelectedItem();
            String  query="delete from client where id_client="+client.getId_client()+"";
            client.exreq(query);
            loadData();
        }
    }
    @FXML
    void imprimer(ActionEvent event) {
//        Client client=new Client();
        Stage stage = new Stage();
        PrinterJob printerJob = PrinterJob.createPrinterJob();
        if(printerJob.showPrintDialog(stage.getOwner()) && printerJob.printPage(tab_info))
            printerJob.endJob();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initiaCols();
        loadData();

    }
}
