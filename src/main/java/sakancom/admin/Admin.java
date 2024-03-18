package sakancom.admin;

import sakancom.admin.requestservice.AdminHouseRequest;
import sakancom.db.RequestedHouseConnecter;
import sakancom.tenant.AdvertisementHousePrinter;
import sakancom.testinput.ValidateInput;
import sakancom.dashboard.AdminDashboard;

import java.util.Scanner;

public class Admin {
   private RequestedHouseConnecter requestedHouseConnecter;
   private HouseTablePrinter houseTablePrinter;
   private AdminHouseRequest adminHouseRequest;
   private StringBuilder stringBuilder; //<-----------------------------------------------------

    public AdminDashboard getAdminDashBoard() {
        return adminDashBoard;
    }

    public void setAdminDashBoard(AdminDashboard adminDashBoard) {
        this.adminDashBoard = adminDashBoard;
    }

    private AdminDashboard adminDashBoard ;


    private ValidateInput validateInput;
    public ValidateInput getValidateInput() {
        return validateInput;
    }
    public void setValidateInput(ValidateInput validateInput) {
        this.validateInput = validateInput;
    }

    private Scanner scanner;
    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }
    public Scanner getScanner() {
        return scanner;
    }

    public Admin(Scanner scanner,ValidateInput validateInput) {
        this.scanner=scanner;
        this.validateInput=validateInput;
//------------------------------------------------------------------------
        this.requestedHouseConnecter = new RequestedHouseConnecter();
        stringBuilder= new StringBuilder();
        this.houseTablePrinter =new HouseTablePrinter(stringBuilder);
//---------------------------------------------------------------------------
        this.adminHouseRequest = new AdminHouseRequest(requestedHouseConnecter,houseTablePrinter);
        adminHouseRequest.setStringBuilder(stringBuilder);
        adminHouseRequest.setScanner(scanner);
        adminHouseRequest.setValidateInput(getValidateInput());
        //--------------------------------------------------------
        AdvertisementHousePrinter advertisementHousePrinter=new AdvertisementHousePrinter(stringBuilder);
        AdminViewReservation adminViewReservation= new AdminViewReservation
                (requestedHouseConnecter,advertisementHousePrinter);

        if(adminDashBoard==null){
            adminDashBoard= new AdminDashboard(scanner,adminHouseRequest,adminViewReservation);
        }
        else{
            this.adminDashBoard=getAdminDashBoard();
        }
        adminDashBoard.setValidateInput(validateInput);
    }




}
