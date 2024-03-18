package sakancom.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TenantConnecter {

    private  Connection connect;
    private  PreparedStatement preparedstatement;
    private  ResultSet resultSet;

    public  void setPreparedstatement(PreparedStatement preparedstatement) {
        this.preparedstatement = preparedstatement;
    }
    public  void setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
    }
    public  void setConnect(Connection connect) {
        this.connect = connect;
    }




        public ResultSet getTenantInfo(String sql){
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


