package sample.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.assests.db.Dbconnect;

import java.sql.*;

public class Category {
    int idcategory;
    String namecategory,description;

    public Category(int idcategory, String namecategory, String description) {
        this.idcategory = idcategory;
        this.namecategory = namecategory;
        this.description = description;
    }

    public Category() {

    }

    public int getIdcategory() {
        return idcategory;
    }

    public String getNamecategory() {
        return namecategory;
    }

    public void setIdcategory(int idcategory) {
        this.idcategory = idcategory;
    }

    public void setNamecategory(String namecategory) {
        this.namecategory = namecategory;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return namecategory;
    }

    public String getDescription() {
        return description;
    }
    public ObservableList<Category> show(String re){
        ObservableList<Category> tab_data= FXCollections.observableArrayList();
        try{
            Connection connection= Dbconnect.getInstance().getConnection();
            Statement statement= connection.createStatement();
            ResultSet resultSet= statement.executeQuery(re);
            while (resultSet.next()){
                Category d=new Category(resultSet.getInt("idcategory"),resultSet.getString("namecategory"),
                       resultSet.getString("comment"));
                tab_data.add(d);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tab_data;
    }
    Dbconnect   k = new Dbconnect();
    public void exreq(String req) {
        PreparedStatement ps;
        try {
            ps = k.getConnection().prepareStatement(req);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    public  void updatecategory(int id,String namecategorie,String desc){
        PreparedStatement ps;
        try {
            String req="update  category set namecategory= ? ,comment= ?    where idcategory= ? ;";
            ps = k.getConnection().prepareStatement(req);
            System.out.println("id is : "+id);
            ps.setString(1,namecategorie);
            ps.setString(2,desc);
            ps.setInt(3,id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }
}
