package sample.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.sun.security.ntlm.NTLMException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sample.assests.db.Dbconnect;
import sample.model.Category;
import sample.model.Client;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EditClientController implements Initializable {
    @FXML
    private Button closeButton;

    @FXML
    private JFXTextField tele;

    @FXML
    private JFXTextField namecl;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXTextField id;

    @FXML
    private JFXTextField total_delivred;

    @FXML
    private JFXTextField total_canceled;

    @FXML
    private JFXTextField sexe;
    Stage stage;

    Dbconnect co=new Dbconnect();
    Client cl= new Client();
    Connection connection= Dbconnect.getInstance().getConnection();
    // public void fillcomobox(String s){
    //            PreparedStatement ps;
    //           ObservableList<Client> c= FXCollections.observableArrayList();
    //           try {
    //
    //               String req="select * from client";
    //               ps = k.getConnection().prepareStatement(req);
    //                ResultSet rs =ps.executeQuery();
    //                  while (rs.next()){
    //                    Client ca =new Client();
    //                   ca.setSexe(rs.getString("sexe"));
    //                    c.add(ca);
    //               }
    //              sexe.setItems(c);
    //               sexe.getSelectionModel().select(0);
    //                //category.setDisable(true);
    //           } catch (Exception e) {
    //               System.out.println(e.toString());
    //           }
    //        }

    public void setclient(Client cl){
        id.setText(cl.getId_client()+"");
        namecl.setText(cl.getNameclient()+"");
        email.setText(cl.getEmail()+"");
        tele.setText(cl.getTele()+"");
        sexe.setText(cl.getSexe()+"");
        total_delivred.setText(cl.getOrder_delevrd()+"");
        total_canceled.setText(cl.getOrder_delevrd()+"");
    }
    @FXML
    void editclient(ActionEvent event) {
  //try { String  query="update  client set nameclient=?,tele=?,email=?,sexe=?,total_order_delivred=?, total_order_cancelled=? where id_client=?";
        //                        PreparedStatement preparedStatement = connection.prepareStatement(query);
        //                        preparedStatement.setInt(7,cl.getId_client());
        //                        preparedStatement.setString(1,namecl.getText());
        //                        preparedStatement.setString(3,email.getText());
        //                        preparedStatement.setString(2,tele.getText());
        //                        preparedStatement.setString(4,sexe.getText());
        //                        preparedStatement.setInt(5,Integer.parseInt(total_delivred.getText()));
        //                        preparedStatement.setInt(6,Integer.parseInt(total_canceled.getText()));
        //                        //preparedStatement.setString(3,status.getText());
        //                        preparedStatement.executeUpdate();
        //                        preparedStatement.close();
        //                        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        //                        stage.close();
        //                    } catch (Exception  e) {
        //                       // System.out.println(e.toString());
        //                        System.out.println("not a number");
        //
        //                    }

            //String req=" update or REPLACE  client set nameclient='"+name+"',tele='"+Tele1+"',email='"+email1+"',sexe='"+sexe1+"',total_order_delivred='"+total+"',total_order_cancelled='"+total2+"' ";
            //int total=0;
            //int totalo=0;
        // int id=cl.getId_client();
        System.out.println("test testna fnct " +
                namecl.getText());
                String name=namecl.getText();
                     String email1=email.getText();
                     String Tele1=tele.getText();
                     String sexe1=sexe.getText();
                     String Tot=total_delivred.getText();
                     String TO=total_canceled.getText();
                    // total=Integer.parseInt(Tot);
                    // totalo=Integer.parseInt(TO);
//                     String req=" update  client set nameclient='"+name+"',tele='"+Tele1+"',email='"+email1+"',sexe='"+sexe1+"',total_order_delivred="+Integer.parseInt(Tot)+",total_order_cancelled="+Integer.parseInt(TO)+"  where id_client="+cl.getId_client()+" ;";

//        ,Tele1,email1,sexe1,Integer.parseInt(Tot),Integer.parseInt(TO)
        cl.updateClient(Integer.parseInt(id.getText()),namecl.getText(),Tele1,email1,sexe1,Integer.parseInt(Tot),Integer.parseInt(TO));
                        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
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
