package sakancom.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    private DataBaseConnection(){}

    public static Connection connectDb(){
        try{
            String password = System.getProperty("database.password");
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost/sakancom","root",password);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
