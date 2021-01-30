package sample.model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import sample.assests.db.Dbconnect;

import java.sql.*;

public class Product {
   public String nameproduct,category,description;
    public int id_product,qunatity;
    public int price;

    public void setIdcategory(int idcategory) {
        this.idcategory = idcategory;
    }

    public int getIdcategory() {
        return idcategory;
    }

    int idcategory;

    public Product(int id_product, String nameproduct, int price, int qunatity, String category, String description) {
        this.id_product = id_product;
        this.nameproduct = nameproduct;
        this.price = price;
        this.qunatity= qunatity;
        this.category = category;
        this.description = description;
    }

    public Product() {

    }

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public void setNameproduct(String nameproduct) {
        this.nameproduct = nameproduct;
    }

    public void setPrice( int price) {
        this.price = price;
    }

    public void setQunatity(int qunatity) {
        this.qunatity= qunatity;
    }

    public void setCategory(String category) {  this.category = category;
       }


    public void setDescription(String description) {
        this.description = description;
    }

    public String getNameproduct() {
        return nameproduct;
    }

    public int getPrice() {
        return price;
    }

    public int getQunatity() {
        return qunatity;
    }

    public String getCategory() {    return category;
        }

    public String getDescription() {
        return description;
    }
    Dbconnect   k = new Dbconnect();
//--------------------------------------- function----------------------------//
    public void exreq(String req) {
        PreparedStatement ps;
        try {
            ps = k.getConnection().prepareStatement(req);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    public void updateProduct(int id,String nameproduct,int price,int qunatite,int c,String desc) {
        PreparedStatement ps;
        try {
//            String req="update  client set nameclient= ? where id_client= ? ;";
            String req="update  product set id_category=?, nameproduct= ? ,price= ? ,qunatity= ? ,description= ?   where id_product= ? ;";
            ps = k.getConnection().prepareStatement(req);
            System.out.println("id is :"+id);
            ps.setInt(1,c);
            ps.setString(2,nameproduct);
            ps.setInt(3,price);
            ps.setInt(4,qunatite);
            ps.setString(5,desc);
            ps.setInt(6,id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public ObservableList<Product> show(String re){
        ObservableList<Product> tab_data= FXCollections.observableArrayList();
        try{
            Connection connection= Dbconnect.getInstance().getConnection();
            Statement statement= connection.createStatement();
            ResultSet resultSet= statement.executeQuery(re);
            while (resultSet.next()){
                Product d=new Product(resultSet.getInt("id_product"),resultSet.getString("nameproduct"),
                        resultSet.getInt("price"),resultSet.getInt("qunatity"),
                        resultSet.getString("namecategory"),
                        resultSet.getString("description"));
                d.setIdcategory(resultSet.getInt("id_category"));
                tab_data.add(d);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tab_data;
    }
    public ObservableList<Product> show2(String re){
        ObservableList<Product> tab_data= FXCollections.observableArrayList();
        try{
            Connection connection= Dbconnect.getInstance().getConnection();
            Statement statement= connection.createStatement();
            ResultSet resultSet= statement.executeQuery(re);
            while (resultSet.next()){
                Product d=new Product();

                     d.setId_product(resultSet.getInt("id_product"));
                     d.setPrice(resultSet.getInt("price"));

//                ,resultSet.getString("nameproduct"),
//                        ,resultSet.getInt("qunatity"),
////                        resultSet.getString("namecategory"),
//                        resultSet.getString("description"));
//                d.setIdcategory(resultSet.getInt("id_category"));
                tab_data.add(d);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tab_data;
    }


    public ObservableList<BarChart.Data>barchart(){
        ObservableList<BarChart.Data> piDat= FXCollections.observableArrayList();
        Product P =new Product();

        try{
            Connection connection= Dbconnect.getInstance().getConnection();
            Statement statement= connection.createStatement();
            Statement statement2= connection.createStatement();
            ResultSet rs= statement.executeQuery("select DISTINCT namecategory from product inner join category on idcategory=id_category ");
            while(rs.next()){
                P.setNameproduct(rs.getString("namecategory"));
                String lib=rs.getString("namecategory");
                ResultSet rss= statement2.executeQuery("SELECT sum(number) from  orde inner join product on product.id_product=orde.idproduct INNER join client as c on  idclient=c.id_client  where nameproduct='"+lib+"'");
                while(rss.next()){
                    rss.getInt("sum(number)");
                    int va=rss.getInt("sum(number)");
                    System.out.println(va+ " "+ lib );
                    piDat.add(new BarChart.Data(lib,va));
                }
            }

        }catch (Exception e){ System.out.println(e.getMessage()); }
        return piDat;
    }
    public ObservableList<PieChart.Data>chrats(){
        ObservableList<PieChart.Data> piDat= FXCollections.observableArrayList();
        Product P =new Product();

        try{
            Connection connection= Dbconnect.getInstance().getConnection();
            Statement statement= connection.createStatement();
            Statement statement2= connection.createStatement();
            ResultSet rs= statement.executeQuery("select DISTINCT nameproduct from orde inner join product on id_product=idproduct inner join client on idclient =id_client");
            while(rs.next()){
                P.setNameproduct(rs.getString("nameproduct"));
                String lib=rs.getString("nameproduct");
                ResultSet rss= statement2.executeQuery("SELECT sum(number) from  orde inner join product on product.id_product=orde.idproduct INNER join client as c on  idclient=c.id_client  where nameproduct='"+lib+"'");
                while(rss.next()){
                    rss.getInt("sum(number)");
                    int va=rss.getInt("sum(number)");
                    System.out.println(va+ " "+ lib );
                    piDat.add(new PieChart.Data(lib,va));
                }
            }

        }catch (Exception e){ System.out.println(e.getMessage()); }
        return piDat;
    }



    @Override
    public String toString() {
        return getNameproduct() ;
    }


}
