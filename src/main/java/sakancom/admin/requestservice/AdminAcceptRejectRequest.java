package sakancom.admin.requestservice;
import io.cucumber.core.logging.Logger;
import io.cucumber.core.logging.LoggerFactory;
import sakancom.db.RequestedHouseConnecter;

import java.sql.ResultSet;
import java.sql.SQLException;

/*
Need to be reviewed !
 */
public class AdminAcceptRejectRequest {
    private static final Logger logger=  LoggerFactory.getLogger(AdminAcceptRejectRequest.class);

    private final RequestedHouseConnecter requestedHouseConnecter; // injected

    public boolean getIsRequestAcceptedRejectedSuccessfully() {
        return isRequestAcceptedRejectedSuccessfully;
    }

    private boolean isRequestAcceptedRejectedSuccessfully;
    public AdminAcceptRejectRequest(RequestedHouseConnecter requestedHouseConnecter) {
        this.requestedHouseConnecter = requestedHouseConnecter ;

    }



    public void acceptRequest(int reqID) throws SQLException {
        // Status 1 : mean Accepted
        requestedHouseConnecter.setStatusRequest(reqID,1);
        isRequestAcceptedRejectedSuccessfully=isAcceptedRejected(reqID,true);
       if( isRequestAcceptedRejectedSuccessfully)
          logger.info(()->"successfully accepted !");
    }
    public void rejectRequest(int reqID) throws SQLException {
        // Rejected Mean 0
        requestedHouseConnecter.setStatusRequest(reqID,0);
        isRequestAcceptedRejectedSuccessfully=isAcceptedRejected(reqID,false);
        if( isRequestAcceptedRejectedSuccessfully)
            logger.info(()->"successfully rejected !");
    }



    public boolean isRequestIdExistence(int requestId){
        boolean isIdExist=false;
        ResultSet r=requestedHouseConnecter.getPendingRequest
                ("select RequestID from requesthouse where RequestID='"+requestId+"'");
        try {
            isIdExist=r.next();

            requestedHouseConnecter.closeConnectionResource();
            r.close();

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return  isIdExist;
    }

    public boolean isAcceptedRejected(int requestId,boolean acceptedRejected){
        boolean isTheReqAccepted=false;
        try {
            int status=acceptedRejected?1:0;
            ResultSet r = requestedHouseConnecter.getPendingRequest
                    ("select Status from requesthouse WHERE RequestID="+requestId+"");
             if(r.next()) {
                 isTheReqAccepted=r.getInt("Status") ==status;
             }

            requestedHouseConnecter.closeConnectionResource();
            r.close();
        }
        catch (SQLException e){
           e.printStackTrace();
        }
        return isTheReqAccepted;

    }
}
