package sample.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.assests.db.Dbconnect;

import java.sql.*;

public class Client {
    int id_client,order_delevrd,order_cancelled;
    String nameclient,email,sexe,tele;

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public int getOrder_delevrd() {
        return order_delevrd;
    }

    public void setOrder_delevrd(int order_delevrd) {
        this.order_delevrd = order_delevrd;
    }


    Dbconnect k = new Dbconnect();

    public Client(int id_client,String nameclient ,String tele, String email, String sexe, int order_delevrd, int order_cancelled) {
        this.id_client = id_client;
        this.nameclient = nameclient;
        this.tele = tele;
        this.email = email;
        this.order_cancelled = order_cancelled;
        this.sexe=sexe;
        this.order_delevrd=order_delevrd;
    }

    public Client() {}

    public int getId_client() {
        return id_client;
    }

    public void setId(int id_client) {
        this.id_client= id_client;
    }

    public void setNameclient(String nameclient) {
        this.nameclient = nameclient;
    }

    public void setTele( String tele) {
        this.tele = tele;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //public void setOrder_delivred(String order_delivred) { this.order_delivred = order_delivred; }

    public void setOrder_cancelled(int order_cancelled) {    this.order_cancelled = order_cancelled;
        }


    //public void setPrice(int price) {    this.price = price;
    //    }

    public String getNameclient() {
        return nameclient;
    }

    public String getTele() {
        return tele;
    }

    public String getEmail() {
        return email;
    }

    //public String getOrder_delivred() {   return order_delivred;
    //    }


    public int getOrder_cancelled() {    return order_cancelled;
       }


    //public int getPrice() {  return price;
    //    }


    public void exreq(String req) {
        PreparedStatement ps;
        try {
            ps = k.getConnection().prepareStatement(req);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }


    public void updateClient(int id,String nameclient,String tele,String email,String sexe,int Tot,int To) {
        PreparedStatement ps;
        try {
//            String req="update  client set nameclient= ? where id_client= ? ;";
            String req="update  client set nameclient= ? ,tele= ? ,email= ? ,sexe= ? ,total_order_delivred= ? ,total_order_cancelled= ?  where id_client= ? ;";
            ps = k.getConnection().prepareStatement(req);
            System.out.println("id is : "+id);
            ps.setString(1,nameclient);
            ps.setString(2,tele);
            ps.setString(3,email);
            ps.setString(4,sexe);
            ps.setInt(5,Tot);
            ps.setInt(6,To);
            ps.setInt(7,id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }





    public ObservableList<Client> show(String re){
        ObservableList<Client> tab_data= FXCollections.observableArrayList();
        try{
            Connection connection= Dbconnect.getInstance().getConnection();
            Statement statement= connection.createStatement();
            ResultSet resultSet= statement.executeQuery(re);
            while (resultSet.next()){
                Client d=new Client(resultSet.getInt("id_client"),resultSet.getString("nameclient"),
                        resultSet.getString("tele"),resultSet.getString("email"),resultSet.getString("sexe"),resultSet.getInt("total_order_delivred"),resultSet.getInt("total_order_cancelled"));
                tab_data.add(d);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tab_data;
    }

    @Override
    public String toString() {
        return nameclient;
    }
}
