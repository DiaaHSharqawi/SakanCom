package sakancom.admin.requestservice;

import sakancom.admin.HouseTablePrinter;
import sakancom.db.OwnerConnecter;
import sakancom.db.RequestedHouseConnecter;
import sakancom.testinput.ValidateInput;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AdminHouseRequest {
    private ValidateInput validateInput;
    private  Scanner scanner;
    private final RequestedHouseConnecter requestedHouseConnecter;
    private final HouseTablePrinter houseTablePrinter;

    public void setStringBuilder(StringBuilder stringBuilder) {
        this.stringBuilder = stringBuilder;
    }

    private StringBuilder stringBuilder; //<---------------------------------

    public AdminHouseRequest(RequestedHouseConnecter requestedHouseConnecter
                            , HouseTablePrinter houseTablePrinter) {
        this.requestedHouseConnecter = requestedHouseConnecter;
        this.houseTablePrinter = houseTablePrinter;
    }
    //------------------------------------------------------>
    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }
    public void setValidateInput(ValidateInput validateInput) {
        this.validateInput = validateInput;
    }

    public void setAdminHouseRequestMenu(AdminHouseRequestMenu adminHouseRequestMenu) {
        this.adminHouseRequestMenu = adminHouseRequestMenu;
    }
//---------------------------------------------------------->
    private AdminAcceptRejectRequest adminAcceptRejectRequest;
    private OwnerConnecter ownerConnecter;
    private AdminViewOwner viewOwner;



    private AdminHouseRequestMenu adminHouseRequestMenu;
    public void setUpRequestHouseObject(){
        // set up AdminAcceptRejectRequest
         adminAcceptRejectRequest=new AdminAcceptRejectRequest(requestedHouseConnecter);
        // set up AdminViewOwner
         ownerConnecter= new OwnerConnecter();
         viewOwner = new AdminViewOwner(ownerConnecter,stringBuilder);
        // set up AdminHouseRequestMenu
         adminHouseRequestMenu = new AdminHouseRequestMenu(adminAcceptRejectRequest, viewOwner);
        adminHouseRequestMenu.setValidateInput(validateInput);
        adminHouseRequestMenu.setScanner(scanner);
    }



    //---------------------------------------------->
    public void viewRequestedHouse()  {
        try{
            /*This code query table Request information and print it as a table . */
        if(adminAcceptRejectRequest==null && ownerConnecter==null
           && viewOwner ==null &&adminHouseRequestMenu==null) {
            setUpRequestHouseObject();
        }
        while (adminHouseRequestMenu.getChoice()!=4) {
            ResultSet r = requestedHouseConnecter.
                    getPendingRequest("SELECT * from requesthouse where Status=-1");
            houseTablePrinter.printTableHeader();
            houseTablePrinter.printRequestHouseTable(r);
            r.close();
            requestedHouseConnecter.closeConnectionResource();

            adminHouseRequestMenu.menuHandler();
        }
        adminHouseRequestMenu.setChoice(0);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    //----------------------------------------------->
    public void viewAcceptedRejected(boolean acceptedRejected)  {
        int status=acceptedRejected?1:0;
       ResultSet r= requestedHouseConnecter.
                getPendingRequest("SELECT * from requesthouse where Status="+status+"");
       try {
           houseTablePrinter.printTableHeader();
           houseTablePrinter.printRequestHouseTable(r);
           requestedHouseConnecter.closeConnectionResource();
           r.close();
       }
       catch (SQLException e){
           e.printStackTrace();
       }
    }
}
