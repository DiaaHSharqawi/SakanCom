package sakancom.tenant;


import io.cucumber.core.logging.Logger;
import io.cucumber.core.logging.LoggerFactory;

import java.sql.ResultSet;

public class BookedHouseTablePrinter {
    private static final Logger logger=  LoggerFactory.getLogger(BookedHouseTablePrinter.class);
    private static final  String TABLE_LINE="+--------------------+----------------------+---------------------------+-----------------------------------------+\n";

    public BookedHouseTablePrinter(StringBuilder tableStringBuilder) {
        this.tableStringBuilder = tableStringBuilder;
    }

    private final StringBuilder tableStringBuilder;

    public void printTenantInfoTable(ResultSet r) {
        String data;
            try {
                printTableHeader();
                while (r.next()) {
                     data=
                    String.format ("| %-18s | %-20s | %-25s | %-40s|%n",
                            (r.getString(1)),
                            r.getString(2),
                            r.getString(3),
                            r.getString(4));
                    tableStringBuilder.append(data);

                }
                tableStringBuilder.append(TABLE_LINE);
            } catch (Exception e) {
                e.printStackTrace();
            }
            logger.info(tableStringBuilder::toString);
            tableStringBuilder.setLength(0);
        }

        //------------------------------------>
        public void printTableHeader() {
            String tableHeaderText="|     Booking ID     |      tenant Name     |        Owner name         |               Booking Date              |\n";
            String tableHeader = "\n" + TABLE_LINE + tableHeaderText + TABLE_LINE;
            tableStringBuilder.append(tableHeader);
        }


    }

