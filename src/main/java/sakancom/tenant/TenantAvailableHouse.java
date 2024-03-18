package sakancom.tenant;

import sakancom.db.RequestedHouseConnecter;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TenantAvailableHouse {
    public boolean getIsPrinted() {
        return isPrinted;
    }

    private boolean isPrinted;
    public TenantAvailableHouse(RequestedHouseConnecter requestedHouseConnecter,
                                AdvertisementHousePrinter advertisementHousePrinter) {
        this.requestedHouseConnecter = requestedHouseConnecter;
        this.advertisementHousePrinter = advertisementHousePrinter;
    }

    private final RequestedHouseConnecter requestedHouseConnecter;
    private final AdvertisementHousePrinter advertisementHousePrinter;

    public void getAvailableHouse()  {
        try {
            ResultSet r = requestedHouseConnecter.
                    getPendingRequest("SELECT * from requesthouse where Status = 1");
            advertisementHousePrinter.printAvailableHouse(r);

            requestedHouseConnecter.closeConnectionResource();
            r.close();
            isPrinted=true;
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }




}
