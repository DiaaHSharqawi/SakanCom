package sakancom.db.userconnection;

import sakancom.db.DataBaseConnection;
import sakancom.user.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDataBaseConnecter {
    // DataBase Tools
    private  Connection connect;
    private  PreparedStatement preparedstatement;
    private  ResultSet resultSet;
    private   String sql;

    public  UserDataBaseConnecter(){
        connect=null;
        preparedstatement=null;
        resultSet=null;
        sql=null;
    }

    public  User userConnector(String userName, String password){

        sql = "select * from user where username =? and password=?";
        try{
            connect= DataBaseConnection.connectDb();
            if(connect!=null) {
                preparedstatement = connect.prepareStatement(sql);
                preparedstatement.setString(1, userName);
                preparedstatement.setString(2, password);
                resultSet = preparedstatement.executeQuery();
                if (resultSet.next()) {
                    return new User(
                            resultSet.getString("username"),
                            resultSet.getString("password"),
                            resultSet.getString("Type")
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            // need modify
            if(connect!=null){
                try {
                    connect.close();
                    preparedstatement.close();
                    resultSet.close();
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
                }

        }
        return null;
    }
}