package sample.controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.input.MouseEvent;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.scene.chart.AreaChart;
import sample.assests.db.Dbconnect;
import sample.model.Client;
import sample.model.Order;
import sample.model.Product;

import java.net.URL;
import java.util.ResourceBundle;


public class home1Controller implements Initializable {

     @FXML
    PieChart pieChart;

    @FXML
    private BarChart<String,Double> Chart;

   // ObservableList<PieChart.Data> pcData  = FXCollections.observableArrayList();
   // ObservableList<BarChart.Data> pc = FXCollections.observableArrayList();

    @FXML
    private Text totalorder;

    @FXML
    private Text totalclient;

    @FXML
    private Text total;

    @FXML
    private Text totalcategory;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Product p=new Product();
        pieChart.setData(p.chrats());
        pieChart.setTitle("Produits les plus vendus");
        totalClient();
        totalOrder();
        totalPoduct();
        total();
        barcht();

    }
    Dbconnect k = new Dbconnect();
    public void barcht(){
        try{
            Connection connection= Dbconnect.getInstance().getConnection();
            Statement statement= connection.createStatement();
            Statement statement2= connection.createStatement();
            ResultSet rs= statement.executeQuery("select nameproduct , qunatity from product ORDER BY qunatity asc  ");
            XYChart.Series xyC1 = new XYChart.Series();
            XYChart.Series xyC2 = new XYChart.Series();
            xyC1.setName("Produit");
            xyC2.setName("Quantité");

            XYChart.Series<String,Double> series= new XYChart.Series<>();
            while(rs.next()){
                series.getData().add(new XYChart.Data<>(rs.getString(1),rs.getDouble(2)));
            }
            Chart.getData().add(series);

        }catch (Exception e){ System.out.println(e.getMessage()); }

    }
    public void totalOrder(){
        PreparedStatement ps;
        try {
            String req="select count(*) from orde;";
            ps = k.getConnection().prepareStatement(req);
            ResultSet rs =ps.executeQuery();
            while (rs.next()){
                String total=rs.getString("count(*)");
                totalorder.setText(total);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    public void totalClient(){
        PreparedStatement ps;
        try {
            String req="select count(*) from client;";
            ps = k.getConnection().prepareStatement(req);
            ResultSet rs =ps.executeQuery();
            while (rs.next()){
                String total=rs.getString("count(*)");
                totalclient.setText(total);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    public void totalPoduct(){
        PreparedStatement ps;
        try {
            String req="select count(*) from category;";
            ps = k.getConnection().prepareStatement(req);
            ResultSet rs =ps.executeQuery();
            while (rs.next()){
                String total=rs.getString("count(*)");
                totalcategory.setText(total);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    public void total(){
        PreparedStatement ps;
        try {
            String req="select SUM(number*prix) from orde;";
            ps = k.getConnection().prepareStatement(req);
            ResultSet rs =ps.executeQuery();
            while (rs.next()){
                String totalw=rs.getString("SUM(number*prix)");
                total.setText(totalw);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}


