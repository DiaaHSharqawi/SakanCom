package sakancom.db;

import java.sql.*;

public class BookingHouseConnecter {

        private  Connection connect;
        private  PreparedStatement preparedstatement;
        private  ResultSet resultSet;

        public void insertBookedHouse (String tenantUsername,
                                String ownerUsername)      {
            try {
                connect = DataBaseConnection.connectDb();
                assert connect != null;
                String sql = "INSERT INTO bookinghouse( tenant_username, owner_username, booking_date) " +
                        "VALUES (?,?,?)";
                preparedstatement=connect.prepareStatement(sql);
                preparedstatement.setString(1,tenantUsername);
                preparedstatement.setString(2,ownerUsername);
                Date bookingDate=new Date(System.currentTimeMillis());
                preparedstatement.setDate(3,bookingDate);
                preparedstatement.executeUpdate();

            }
            catch (SQLException e){
                e.printStackTrace();
            }
            finally {
                closeConnectionResource();
            }

        }

        public ResultSet getBookedHouse(String tenantUsername,String ownerUsername){

            try {
                connect = DataBaseConnection.connectDb();
                assert connect != null;
                String sql = "select * from bookinghouse where tenant_username=? and owner_username=?  ";
                preparedstatement=connect.prepareStatement(sql);
                preparedstatement.setString(1,tenantUsername);
                preparedstatement.setString(2,ownerUsername);
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


