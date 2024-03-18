package sakancom.db.userconnection;

import sakancom.db.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserInformationExistence {
    public UserInformationExistence() {
        connect=null;
        preparedstatement=null;
        resultSet=null;
        sql=null;
    }

    private  Connection connect;
    private  PreparedStatement preparedstatement;
    private  ResultSet resultSet;
    private   String sql;


    public boolean checkUserNameExistence (String usernameInput){
        connect= DataBaseConnection.connectDb();
        sql = "select username from user where username =?";
        try{

            if(connect!=null) {
                preparedstatement = connect.prepareStatement(sql);
                preparedstatement.setString(1, usernameInput);
                resultSet = preparedstatement.executeQuery();
                if (resultSet.next()) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
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
        return false; // not found
    }
    }

