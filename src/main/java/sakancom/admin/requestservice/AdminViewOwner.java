package sakancom.admin.requestservice;

import io.cucumber.core.logging.Logger;
import io.cucumber.core.logging.LoggerFactory;
import sakancom.db.OwnerConnecter;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminViewOwner {
    private static final Logger logger=  LoggerFactory.getLogger(AdminViewOwner.class);
    public AdminViewOwner(OwnerConnecter ownerConnecter,StringBuilder tableStringBuilder) {

        this.tableStringBuilder=tableStringBuilder;
        this.ownerConnecter = ownerConnecter;
    }
    private static final String TABLE_LINE="+--------------------+----------------------+---------------------------+-----------------------------------------+\n";

    private final OwnerConnecter ownerConnecter;
    private final StringBuilder tableStringBuilder;

    public void adminPrintOwnerTable() {
        ResultSet resultSet = ownerConnecter.getOwnerInfo
                ("SELECT * FROM owner join requesthouse on " +
                        "owner.owner_username=requesthouse.OwnerUserName " +
                        "where requesthouse.Status=-1 ");
        try {
                printTableHeader();
                printOwnerInfoTable(resultSet);
                ownerConnecter.closeConnectionResource();
                resultSet.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        logger.info(tableStringBuilder::toString);


    }

    public  void printOwnerInfoTable(ResultSet r){
        String data;
        try {
            while (r.next()){
                 data=
                        String.format ("| %-18s | %-20s | %-25s | %-40s|%n",
                        r.getString(1),
                        r.getString(2),
                        r.getString(3),
                        r.getString(4));
                tableStringBuilder.append(data);

            }
            tableStringBuilder.append(TABLE_LINE);
            r.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    //------------------------------------>
    public  void printTableHeader(){
        String tableHeaderText="|   Owner UserName   |      Owner Name      |        Owner Phone        |               Owner Email               |\n";
        String tableHeader= "\n"+TABLE_LINE+tableHeaderText+TABLE_LINE;
        tableStringBuilder.append(tableHeader);


    }



}
