package sakancom.tenant;

import io.cucumber.core.logging.Logger;
import io.cucumber.core.logging.LoggerFactory;
import sakancom.db.OwnerConnecter;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TenantViewOwnerInfo {
    private static final Logger logger=  LoggerFactory.getLogger(TenantViewOwnerInfo.class);

    private final OwnerConnecter ownerConnecter;
    private final StringBuilder tableStringBuilder;
    private static final  String TABLE_LINE="+--------------------+----------------------+---------------------------+-----------------------------------------+\n";
    public TenantViewOwnerInfo(OwnerConnecter ownerConnecter,StringBuilder tableStringBuilder) {
        this.ownerConnecter = ownerConnecter;
        this.tableStringBuilder=tableStringBuilder;
    }

    public void printOwnerInfo(String ownerUsername){
        ResultSet resultSet = ownerConnecter.getOwnerInfo
                ("SELECT * FROM owner WHERE owner_username ='"+ownerUsername+"'");
        try {
            printTableHeader();
            printOwnerInfoTable(resultSet);
            ownerConnecter.closeConnectionResource();
            resultSet.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }

    public  void printOwnerInfoTable(ResultSet r){
        try {
            while (r.next()){
                String data= String.format ("| %-18s | %-20s | %-25s | %-40s|%n",
                        (r.getString(1)),
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
        logger.info(tableStringBuilder::toString);
    }
    //------------------------------------>
    public  void printTableHeader(){
        String tableHeaderText="|   Owner UserName   |      Owner Name      |        Owner Phone        |               Owner Email               |\n";
        String tableHeader = "\n" + TABLE_LINE + tableHeaderText + TABLE_LINE;
        tableStringBuilder.append(tableHeader);
    }
}
