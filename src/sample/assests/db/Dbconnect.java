package sample.assests.db;

import org.sqlite.SQLiteConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Dbconnect {

    public static Dbconnect getInstance() {
      return new Dbconnect();
    }
        public Connection  getConnection() {
            String  connect_string="jdbc:sqlite:nvdata.db";
            Connection connection =null;
            try {
                Class.forName("org.sqlite.JDBC");
                System.out.println("con etabli");

                SQLiteConfig config = new SQLiteConfig(); //I add this configuration
                config.enforceForeignKeys(true);

                connection = DriverManager.getConnection(connect_string,config.toProperties());
            }catch (SQLException e){
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return connection;
        }


}

