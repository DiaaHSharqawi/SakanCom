package Sakancom.acceptance.TenantTesting;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.mockito.Mockito;
import sakancom.db.TenantConnecter;
import sakancom.tenant.TenantViewInfo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class ViewTenantInformationFeatureStep {

        private static TenantConnecter tenantConnecter;
        private static ResultSet resultSet;
        private String tenantUsername;
        private static StringBuilder stringBuilder;
        //--------------------------------------------------
        private static TenantViewInfo tenantViewInfo; // new obj
        @BeforeAll
        public static void before_all() throws SQLException {
            resultSet= Mockito.mock(ResultSet.class);
            tenantConnecter=Mockito.mock(TenantConnecter.class);

            tenantViewInfo=new TenantViewInfo(tenantConnecter,stringBuilder);
        }
        @AfterAll
        public static void after_all() throws Exception {}

        public void setUpTableColumn(List<Map<String,String>> data) throws SQLException {
            this.tenantUsername=data.get(0).get("TenantUsername");
            when(resultSet.getString(1)).thenReturn(data.get(0).get("TenantUsername"));
            when(resultSet.getString(2)).thenReturn(data.get(0).get("TenantName"));
            when(resultSet.getString(3)).thenReturn(data.get(0).get("TenantEmail"));
            when(resultSet.getString(4)).thenReturn(data.get(0).get("TenantPhone"));
        }



    @Given("The following Tenant table:")
        public void theFollowingTenantTable(DataTable dataTable) throws SQLException {
            List<Map<String,String>> data= dataTable.asMaps(String.class,String.class);
        setUpTableColumn(data);


    }

        @When("I choose to view the Tenant House")
        public void iChooseToViewTheTenantHouse() throws SQLException {
            when( tenantConnecter.getTenantInfo
                    ("SELECT * FROM tenant WHERE tenant_username='"+tenantUsername+"'")).thenReturn(resultSet);

            when(resultSet.next()).thenReturn(true,false);
            tenantViewInfo.printTenantTable(tenantUsername);
        }


        @Then("I should see the Tenant Details  displayed on the screen")
        public void iShouldSeeTheTenantDetailsDisplayedOnTheScreen() {
            assertTrue(  tenantViewInfo.getIsPrinted());
        }

}
