package sakancom.tenant;


import sakancom.db.OwnerConnecter;
import sakancom.db.RequestedHouseConnecter;
import sakancom.db.BookingHouseConnecter;
import sakancom.db.TenantConnecter;
import sakancom.testinput.ValidateInput;
import sakancom.dashboard.TenantDashBoard;

import java.util.Scanner;

public class Tenant {

    public TenantDashBoard getTenantDashBoard() {
        return tenantDashBoard;
    }

    private final TenantDashBoard tenantDashBoard;

    public  Tenant(Scanner scanner
            ,ValidateInput validateInput){
        //--------------------------------------------------------------------
        StringBuilder stringBuilder = new StringBuilder(); //<----------------

        RequestedHouseConnecter requestedHouseConnecter = new RequestedHouseConnecter();
        AdvertisementHousePrinter advertisementHousePrinter = new AdvertisementHousePrinter(stringBuilder);//<------
        TenantAvailableHouse tenantAvailableHouse = new TenantAvailableHouse(
                requestedHouseConnecter
                , advertisementHousePrinter
        );
        //------------------------------------------------------------------
        TenantConnecter tenantConnecter = new TenantConnecter();
        TenantViewInfo tenantViewInfo = new TenantViewInfo(tenantConnecter,stringBuilder);//<----

        OwnerConnecter ownerConnecter = new OwnerConnecter();
        TenantViewOwnerInfo tenantViewOwnerInfo = new TenantViewOwnerInfo(ownerConnecter,stringBuilder);


        BookedHouseTablePrinter bookedHouseTablePrinter = new BookedHouseTablePrinter(stringBuilder);//<----
        BookingHouseConnecter bookingHouseConnecter = new BookingHouseConnecter();
        TenantBookedHouse tenantBookedHouse = new TenantBookedHouse(bookingHouseConnecter, bookedHouseTablePrinter);

        //-------------------------------------------------------------------------------
        TenantBookAccommodation tenantBookAccommodation = new TenantBookAccommodation(tenantViewOwnerInfo, tenantBookedHouse, tenantViewInfo);
        tenantBookAccommodation.setScanner(scanner);
        tenantBookAccommodation.setValidateInput(validateInput);
        tenantBookAccommodation.setBookingHouseConnecter(bookingHouseConnecter);
        tenantBookAccommodation.setRequestedHouseConnecter(requestedHouseConnecter);
        //------------------------------------------------------------------------------------------
        tenantDashBoard= new TenantDashBoard(tenantAvailableHouse, tenantBookAccommodation);
        tenantDashBoard.setValidateInput(validateInput);
        tenantDashBoard.setScanner(scanner);

    }

}
