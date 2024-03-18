package sakancom.tenant;

import io.cucumber.core.logging.Logger;
import io.cucumber.core.logging.LoggerFactory;
import sakancom.db.RequestedHouseConnecter;
import sakancom.db.BookingHouseConnecter;
import sakancom.testinput.ValidateInput;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class TenantBookAccommodation {
    private static final Logger logger=  LoggerFactory.getLogger(TenantBookAccommodation.class);


    //-----------------------------------------------------------------
    private  RequestedHouseConnecter requestedHouseConnecter; // injected by setter



    private  BookingHouseConnecter bookingHouseConnecter; // injected


    //----------------------------------------------------------
    private ValidateInput validateInput; // injected by setter
    private Scanner scanner; // injected by setter


    private String tenantUsername;
    private String ownerUsername;

    private final TenantViewOwnerInfo tenantViewOwnerInfo;
    private final TenantBookedHouse tenantBookedHouse;
    private final TenantViewInfo tenantViewInfo;

    public TenantBookAccommodation(TenantViewOwnerInfo tenantViewOwnerInfo,
                                   TenantBookedHouse tenantBookedHouse,
                                   TenantViewInfo tenantViewInfo) {
        this.tenantViewOwnerInfo = tenantViewOwnerInfo;
        this.tenantBookedHouse = tenantBookedHouse;
        this.tenantViewInfo = tenantViewInfo;
    }

    //--------------------------------------------------------------
    public void setValidateInput(ValidateInput validateInput) {
        this.validateInput = validateInput;
    }
    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }
    public void setRequestedHouseConnecter(RequestedHouseConnecter requestedHouseConnecter) {
        this.requestedHouseConnecter = requestedHouseConnecter;
    }
    public void setBookingHouseConnecter(BookingHouseConnecter bookingHouseConnecter) {
        this.bookingHouseConnecter = bookingHouseConnecter;
    }
//------------------------------------------------------
    public void bookAccommodation(int accomID,String tenantUsername){
        this.tenantUsername=tenantUsername;
        if(isTheAccommodationIdExistence(accomID)) {
            ResultSet resultSet = requestedHouseConnecter.
                    getPendingRequest("select OwnerUserName from requesthouse where RequestID='" + accomID+"' and Status=1 ");
            try {
                if (resultSet.next()) {
                    ownerUsername = resultSet.getString("OwnerUserName");
                    // status 2 mean Accommodated
                    requestedHouseConnecter.setStatusRequest(accomID,2);
                    bookingHouseConnecter.insertBookedHouse(this.tenantUsername, ownerUsername);
                    menuHandler();
                }
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        else
            logger.error(()->"Not Found !");

    }


    public boolean isTheAccommodationIdExistence(int accommodationID){
        boolean isIdExist=false;
        ResultSet r=requestedHouseConnecter.getPendingRequest
                ("select RequestID from requesthouse where RequestID='"+accommodationID+"'");
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
    public String readChoice(){
        return scanner.next();
    }
//---------------------------------------------------------------
    public void menuHandler() {
        int choice;
        while (true) {
            printBookAccommodation();
            String input = readChoice();
            scanner.nextLine();
            boolean isValidInput = validateInput.itShouldValidateChoice(input, 1, 4);
            if (isValidInput) {
                choice = Integer.parseInt(input);
                switch (choice) {
                    case 1:
                        tenantViewInfo.printTenantTable(this.tenantUsername);
                        break;
                    case 2:
                        tenantViewOwnerInfo.printOwnerInfo(ownerUsername);
                        break;
                    case 3:
                        tenantBookedHouse.getBookedHouse(this.tenantUsername,ownerUsername);
                        break;
                    case 4:
                        break;
                    default:
                        return;
                }
            }
        }


    }
    //-------------------------------------------------------------------

    public void printBookAccommodation(){
        String menu=
             ( "\n")
            +( "╭────────────────────────────╮\n")
            +( "│            Menu            │\n")
            +( "├────────────────────────────┤\n")
            +( "│ 1. view Personal Data      │\n")
            +( "│ 2. View Owner Information  │\n")
            +( "│ 3. view Booked Information │\n")
            +( "│ 4. Exit                    │\n")
            +( "╰────────────────────────────╯\n")
            +( "Enter your choice: ");
        logger.info(()->menu);



    }
}
