package sakancom.admin;
import io.cucumber.core.logging.Logger;
import io.cucumber.core.logging.LoggerFactory;

import java.sql.ResultSet;

public class HouseTablePrinter {
    private static final Logger logger=  LoggerFactory.getLogger(HouseTablePrinter.class);
    private static final  String TABLE_LINE="+------------+----------------+-------------------------+--------------+----------------+-----------------------------------------------------+--------------------+--------------+---------+-----------+---------------+--------+------------------------------------------------+----------------------------------------------------------------------+----------+\n";
    private final StringBuilder tableStringBuilder;
    public HouseTablePrinter(StringBuilder tableStringBuilder) {
        this.tableStringBuilder = tableStringBuilder;
    }

    public  void printRequestHouseTable(ResultSet r){
        String data;
        try {
            while(r.next()){
                 data=String.format("| %-10s | %-14s | %-23s | %-12s | %-14s | %-51s | %-18s | %-12s | %-7s | %-9s | %-13s | %-6s | %-46s | %-68s | %-8s |  %n",
                        r.getString(1),
                        r.getString(2),
                        r.getString(3),
                        r.getString(4),
                        r.getString(5),
                        r.getString(6),
                        r.getString(7),
                        r.getString(8),
                        r.getInt(9),
                        r.getInt(10),
                        r.getString(11),
                        r.getString(12),
                        r.getString(13),
                        r.getString(14),
                        r.getString(15));
                tableStringBuilder.append(data);
            }
            tableStringBuilder.append(TABLE_LINE);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        logger.info(tableStringBuilder::toString);
        tableStringBuilder.setLength(0);

    }
    public  void printTableHeader(){
        String tableHeaderText= "| Request ID |    ownerName   |        HouseName        |     City     |     Street     |                     Description                     |   Contact Number   |  House For   | R/month | # of Room | # of BathRoom |  Area  |                    Features                    |                                 Image                                |  Status  |\n";
        String tableHeader = "\n" + TABLE_LINE + tableHeaderText + TABLE_LINE;
        tableStringBuilder.append(tableHeader);

    }
}
