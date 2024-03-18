package sakancom.tenant;

import io.cucumber.core.logging.Logger;
import io.cucumber.core.logging.LoggerFactory;

import sakancom.db.TenantConnecter;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TenantViewInfo {
    private static final Logger logger=  LoggerFactory.getLogger(TenantViewInfo.class);
    private final StringBuilder tableStringBuilder;
    private static final  String TABLE_LINE="+--------------------+----------------------+---------------------------+-----------------------------------------+\n";
    public boolean getIsPrinted() {
        return isPrinted;
    }

    private boolean isPrinted;
    private final TenantConnecter tenantConnecter;
    public TenantViewInfo(TenantConnecter tenantConnecter,StringBuilder tableStringBuilder) {
        this.tenantConnecter = tenantConnecter;
        this.tableStringBuilder=tableStringBuilder;
    }


        public void printTenantTable(String tenantUsername)  {
            ResultSet resultSet = tenantConnecter.getTenantInfo
                    ("SELECT * FROM tenant WHERE tenant_username='"+tenantUsername+"'");
                printTableHeader();
                printTenantInfoTable(resultSet);
                tenantConnecter.closeConnectionResource();
                try {


                    resultSet.close();
                }
                catch (SQLException e){
                    e.printStackTrace();
                }



        }
        public  void printTenantInfoTable(ResultSet resultSet){
            String data;
            try {
                while (resultSet.next()){
                    data= String.format("| %-18s | %-20s | %-25s | %-40s|%n",
                            (resultSet.getString(1)),
                            resultSet.getString(2),
                            resultSet.getString(4),
                            resultSet.getString(3)
                           );
                    tableStringBuilder.append(data);
                }
                tableStringBuilder.append(TABLE_LINE);
                isPrinted=true;
            }
            catch (Exception e){
                e.printStackTrace();
            }
            logger.info(tableStringBuilder::toString);
            tableStringBuilder.setLength(0);
        }
        //------------------------------------>
        public  void printTableHeader(){
            String tableHeaderText="|   Tenant username  |      Tenant Name     |        Tenant Phone       |               Tenant Email              |\n";
            String tableHeader = "\n" + TABLE_LINE + tableHeaderText + TABLE_LINE;
            tableStringBuilder.append(tableHeader);

        }



    }


