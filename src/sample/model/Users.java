package sample.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.assests.db.Dbconnect;

import java.sql.*;

public class Users {
    int id;
    String userName, lastName, firstName, email, password;

    public Users(){}

    public Users(int id, String userName, String lastName, String firstName, String email, String password){
        this.id = id;
        this.userName = userName;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public ObservableList<Users> usersList(String re){
        ObservableList<Users> tab_data= FXCollections.observableArrayList();
        try{
            Connection cnx= Dbconnect.getInstance().getConnection();
            Statement statement= cnx.createStatement();
            ResultSet resultSet= statement.executeQuery(re);
            while (resultSet.next()){
                Users usersData = new Users(resultSet.getInt("id"),resultSet.getString("username"), resultSet.getString("familyName"), resultSet.getString("firstName"), resultSet.getString("email"), resultSet.getString("password"));
                tab_data.add(usersData);
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

    public  void updateUser(int id, String userName, String lastName, String firstName, String email, String password){
        PreparedStatement ps;
        try {
            String req="update user set username = ?, familyName = ?, firstName = ?, email = ?, password = ? where id = ? ;";
            ps = k.getConnection().prepareStatement(req);
            System.out.println("id is : "+id);
            ps.setString(1, userName);
            ps.setString(2, lastName);
            ps.setString(3, firstName);
            ps.setString(4, email);
            ps.setString(5, password);
            ps.setInt(6,id);
            ps.executeUpdate();
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
    }

}
