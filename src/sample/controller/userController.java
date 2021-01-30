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
import sample.model.Users;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.ResourceBundle;

public class userController  implements Initializable {


    ObservableList<Users> userslist = FXCollections.observableArrayList();
    Dbconnect k = new Dbconnect();

    @FXML
    private TableView<Users> tab_info;

    @FXML
    private TableColumn<Users, Integer> col_userId;

    @FXML
    private TableColumn<Users, String> col_username;

    @FXML
    private TableColumn<Users, String> col_lastname;

    @FXML
    private TableColumn<Users, String> col_firstname;

    @FXML
    private TableColumn<Users, String> col_email;

    @FXML
    private TextField nameus;

    @FXML
    private Button imprimerCompte;

    Users us = new Users();

    @FXML
    void addAccount(ActionEvent event) {
        try
        {
            Stage stage = new Stage();
            FXMLLoader loder=new FXMLLoader();
            loder.setLocation(getClass().getResource("/sample/views2/user/adduser.fxml"));
            loder.load();
            Parent root =loder.getRoot();
            Scene scene=new Scene(root);
            stage.getIcons().add(new Image("/sample/assests/image/finallogo.png"));
            stage.setTitle("Add an Account");
            stage.setScene(scene);

            //set stage borderless
            stage.initStyle(StageStyle.UNDECORATED);

            stage.show();
        }
        catch (Exception e){
            System.out.println(e.getCause());
        }
    }





    @FXML
    void modifierCompte(ActionEvent event) {
        try
        {
            Stage stage = new Stage();
            FXMLLoader loder = new FXMLLoader();
            loder.setLocation(getClass().getResource("/sample/views2/user/edituser.fxml"));
            loder.load();
            Parent root = loder.getRoot();
            Scene scene=new Scene(root);
            stage.getIcons().add(new Image("/sample/assests/image/finallogo.png"));
            stage.setTitle("Edit user");
            EditUserController cont = loder.getController();
            Users urs = (Users) tab_info.getSelectionModel().getSelectedItem();
            cont.setUser(urs);

            stage.setScene(scene);

            //set stage borderless
            stage.initStyle(StageStyle.UNDECORATED);

            stage.show();
        }
        catch (Exception e){
            System.out.println(e.getCause());
        }
    }


    @FXML
    void supprimerCompte(ActionEvent event) {
        try
        {
            Stage stage = new Stage();
            FXMLLoader loder = new FXMLLoader();
            loder.setLocation(getClass().getResource("/sample/views2/user/deleteuser.fxml"));
            loder.load();
            Parent root = loder.getRoot();
            Scene scene = new Scene(root);
            stage.getIcons().add(new Image("/sample/assests/image/finallogo.png"));
            stage.setTitle("Delete user");
            deleteUserController cont2 = loder.getController();
            Users urs2 = (Users) tab_info.getSelectionModel().getSelectedItem();
            cont2.setPassUser(urs2);

            stage.setScene(scene);

            //set stage borderless
            stage.initStyle(StageStyle.UNDECORATED);

            stage.show();
        }
        catch (Exception e){
            System.out.println(e.getCause());
        }
    }


    private  void initiaCols(){
        col_userId.setCellValueFactory(new PropertyValueFactory<Users,Integer>("id"));
        col_username.setCellValueFactory(new PropertyValueFactory<Users,String>("userName"));
        col_lastname.setCellValueFactory(new PropertyValueFactory<Users,String>("lastName"));
        col_firstname.setCellValueFactory(new PropertyValueFactory<Users,String>("firstName"));
        col_email.setCellValueFactory(new PropertyValueFactory<Users,String>("email"));
    }
    private  void loadData(){
        tab_info.setItems(us.usersList("select * from user"));
    }

    @FXML
    void refresh(ActionEvent event) {
        loadData();
    }

    @FXML
    void search(ActionEvent event) {

        Users user = (Users) tab_info.getSelectionModel().getSelectedItem();
        String  query="select * from user where username='" + nameus.getText()+"'";
        tab_info.setItems(us.usersList(query));

    }
    @FXML
    void imprimerCompte(ActionEvent event) {
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
