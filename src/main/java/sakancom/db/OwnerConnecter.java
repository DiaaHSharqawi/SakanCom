package sakancom.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OwnerConnecter {

    private  Connection connect;
    private  PreparedStatement preparedstatement;
    private  ResultSet resultSet;

    public void insertOwner(String ownerUsername,
                            String ownerName,
                            String ownerPhone,
                            String ownerEmail)      {
        try {
            connect = DataBaseConnection.connectDb();
            assert connect != null;
            String sql = "INSERT INTO owner(owner_username, owner_name, owner_phone, owner_email) VALUES (?,?,?,?)";
            preparedstatement=connect.prepareStatement(sql);
            preparedstatement.setString(1,ownerUsername);
            preparedstatement.setString(2,ownerName);
            preparedstatement.setString(3,ownerPhone);
            preparedstatement.setString(4,ownerEmail);
            preparedstatement.executeQuery();

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            closeConnectionResource();
        }

    }

    public ResultSet getOwnerInfo(String sql){
        try {
            connect = DataBaseConnection.connectDb();
            assert connect != null;
            preparedstatement=connect.prepareStatement(sql);
            resultSet= preparedstatement.executeQuery();
            return resultSet;

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }



    public void closeConnectionResource() {
        closeConnection();
        closePreparedStatement();
        closeResultSet();
    }



    public void closeConnection(){
        try {
            if (connect != null ) {
                connect.close();
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void closePreparedStatement(){
        try {
            if (preparedstatement != null ) {
                preparedstatement.close();
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void closeResultSet(){

        try {
            if (resultSet != null ) {
                resultSet.close();
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

}
