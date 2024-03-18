package sakancom.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RequestedHouseConnecter {

    private  Connection connect;
    private  PreparedStatement preparedstatement;
    private  ResultSet resultSet;

    public ResultSet getPendingRequest(String sql){
        try {
                connect = DataBaseConnection.connectDb();
                assert connect!=null;
                preparedstatement=connect.prepareStatement(sql);
                resultSet=preparedstatement.executeQuery();
                return resultSet;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void removePendingRequest(int requestId){
        try {
            String sql = "delete from requesthouse where RequestID=?";
            connect = DataBaseConnection.connectDb();
            assert connect != null;
            preparedstatement = connect.prepareStatement(sql);

            preparedstatement.setInt(1,requestId);
            preparedstatement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            closeConnectionResource();
        }


    }


    public void setStatusRequest(int requestID,int status) throws SQLException {
        String sql="UPDATE requesthouse SET Status= ?  where RequestID= ? ";
        connect=DataBaseConnection.connectDb();
        assert connect!=null;
        preparedstatement=connect.prepareStatement(sql);
        preparedstatement.setString(1,Integer.toString( status));
        preparedstatement.setString(2,Integer.toString(requestID));
         preparedstatement.executeUpdate();
        connect.close();
        preparedstatement.close();
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


