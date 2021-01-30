package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class MasterpageController {


    @FXML
    private BorderPane main;

    @FXML
    private AnchorPane nav;

    @FXML
    private AnchorPane sub;

    @FXML
    void route1(ActionEvent event) {
        System.out.println("route1");

    }

    @FXML
    void route2(ActionEvent event) {




        NavRouter("command");
        System.out.println("route2");
    }


        public void NavRouter(String file){
        Pane View;
            try {
                View = FXMLLoader.load(getClass().getResource("/sample/views/"+file+".fxml"));
                main.setCenter(View);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }


    }

}
