package sakancom.tenant;

import sakancom.db.BookingHouseConnecter;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TenantBookedHouse {
    public boolean getIsPrinted() {
        return isPrinted;
    }

    private boolean isPrinted;
    private final BookingHouseConnecter bookingHouseConnecter;
    private final BookedHouseTablePrinter bookedHouseTablePrinter;
    public TenantBookedHouse(BookingHouseConnecter bookingHouseConnecter,
                             BookedHouseTablePrinter bookedHouseTablePrinter) {
        this.bookingHouseConnecter = bookingHouseConnecter;
        this.bookedHouseTablePrinter = bookedHouseTablePrinter;
    }



    public void getBookedHouse(String tenantUsername,String ownerUsername){
        isPrinted=false;
        ResultSet resultSet = bookingHouseConnecter.getBookedHouse(tenantUsername,ownerUsername);
            bookedHouseTablePrinter.printTenantInfoTable(resultSet);
            bookingHouseConnecter.closeConnectionResource();
        try {
            resultSet.close();
            isPrinted=true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
