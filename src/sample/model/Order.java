package sample.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import sample.assests.db.Dbconnect;

import java.sql.*;

public class Order {
    public  int id_o;
    public String nam_client;
    public String product;
    public String status;
    public  int number;
    public  int price;
    public int id_client;
    public int id_product;
    public String date;
    public  int total;

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotal() {
        return total;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public int getId_o() {
        return id_o;
    }

    public String getNam_client() {
        return nam_client;
    }

    public String getProduct() {
        return product;
    }

   public  void setId_o(int id_o) {
       this.id_o = id_o;
   }

    public void setNam_client(String nam_client) {
        this.nam_client = nam_client;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public void setNumber( int number) {
        this.number = number;
    }

    public void setPrice( int price) {
        this.price = price;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int  getNumber() {
        return number;
    }

    public  int  getPrice() {
        return price;
    }

    public  String getStatus() {
        return status;
    }
    Dbconnect   k = new Dbconnect();
    public Order(){}
    public Order(int id_o, String nam_client, String product,  int number,  int  price, String status,String date ,int total) {
        this.id_o = id_o;
        this.nam_client = nam_client;
        this.product = product;
        this.number = number;
        this.price = price;
        this.status = status;
        this.date=date;
        this.total=total;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id_o=" + id_o +
                ", nam_client='" + nam_client + '\'' +
                ", product='" + product + '\'' +
                ", status='" + status + '\'' +
                ", number=" + number +
                ", price=" + price +
                '}';
    }

    public String toString2() {
        return "Order{" +
                "id_o=" + id_o +
                ", nam_client='" + nam_client + '\'' +
                ", product='" + product + '\'' +
                ", status='" + status + '\'' +
                ", number=" + number +
                ", price=" + price +
                "id client"+id_client+
                "id produit"+id_product+
                '}';
    }
    //-----------------------------------//
    public void exreq(String req) {
        PreparedStatement ps;
        try {
            ps = k.getConnection().prepareStatement(req);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    public void update( int id,int c,int p, int numb,String stat ){
        PreparedStatement ps;
        try {
            String req="update  orde set idclient=?, idproduct=?, number=?,status=? where id=?";
            ps = k.getConnection().prepareStatement(req);
            System.out.println("id is :"+id);
            ps.setInt(1,c);
            ps.setInt(2,p);
            ps.setInt(3,numb);
            ps.setString(4,stat);
            ps.setInt(5,id);
            //ps.setInt(1,id);
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }
    public ObservableList<Order>show(String re){
        ObservableList<Order> tab_data= FXCollections.observableArrayList();
        try{
            Connection connection= Dbconnect.getInstance().getConnection();
            Statement statement= connection.createStatement();
            ResultSet resultSet= statement.executeQuery(re);
            while (resultSet.next()){
                Order d=new Order(resultSet.getInt("id"),resultSet.getString("nameclient"),
                        resultSet.getString("nameproduct"),resultSet.getInt("number"),resultSet.getInt("prix"),resultSet.getString("status"),resultSet.getString("date"),resultSet.getInt("number*prix"));
                  d.setId_client(resultSet.getInt("idclient"));
                  d.setId_product(resultSet.getInt("idproduct"));
                tab_data.add(d);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tab_data;
    }


}
