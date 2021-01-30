package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HomeController{
    @FXML
    private Label name;

    @FXML
    private Button closeButton;

    @FXML
    private BorderPane borderPain;
    @FXML
    private Button addOrder;
    @FXML
    public void addOrder(ActionEvent event) {
        NavRouter("new_order");
        }

    @FXML
    private Button home;


    @FXML
    void home(ActionEvent event) {
        NavRouter("satistique");
    }

    @FXML
    private Button client;

    @FXML
    void client(ActionEvent event) {

            NavRouter("client");
    }
    @FXML
    private Button product;

    @FXML
    private Button Order;

    @FXML
    private Button Setting;

    @FXML
    private Button compte;

    @FXML
    void product(ActionEvent event) {
        NavRouter("product");
    }

    @FXML
    void Order(ActionEvent event) {
        NavRouter("order");
    }

    @FXML
    void Setting(ActionEvent event) {
        NavRouter("categorie");
    }

    @FXML
    void compte(ActionEvent event) {
        NavRouter("compte");
    }




    public void NavRouter(String file){
        Pane View;
        Stage primaryStage = new Stage();
        try {
            View = FXMLLoader.load(getClass().getResource("/sample/views2/"+file+".fxml"));
            primaryStage.getIcons().add(new Image("/sample/assests/image/finallogo.png"));

            borderPain.setCenter(View);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }


    }



    //get name
    public void setLabelText(String text){
        name.setText("Bienvenue : " + text.toUpperCase());
    }

    public void closeButtonAction(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

}
