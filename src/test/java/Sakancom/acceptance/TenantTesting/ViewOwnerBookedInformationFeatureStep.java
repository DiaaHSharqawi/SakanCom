package Sakancom.acceptance.TenantTesting;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.mockito.Mockito;
import sakancom.db.OwnerConnecter;
import sakancom.tenant.TenantViewOwnerInfo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;

public class ViewOwnerBookedInformationFeatureStep {

    private static OwnerConnecter ownerConnecter;
    private static ResultSet resultSet;
    private String ownerUsername;
    //--------------------------------------------------
   private static TenantViewOwnerInfo tenantViewOwnerInfo;
   private  static StringBuilder stringBuilder;

    @BeforeAll
    public static void before_all() throws SQLException {
        resultSet= Mockito.mock(ResultSet.class);
        ownerConnecter=Mockito.mock(OwnerConnecter.class);
        stringBuilder= new StringBuilder();
        tenantViewOwnerInfo=new TenantViewOwnerInfo(ownerConnecter,stringBuilder);
    }
    @AfterAll
    public static void after_all() throws Exception {}
    public void setUpTableColumn(List<Map<String,String>> data) throws SQLException {


        this.ownerUsername=data.get(0).get("OwnerUserName");
        when(resultSet.getString(1)).thenReturn(data.get(0).get("OwnerUserName"));
        when(resultSet.getString(2)).thenReturn(data.get(0).get("OwnerName"));
        when(resultSet.getString(3)).thenReturn(data.get(0).get("OwnerPhone"));
        when(resultSet.getString(4)).thenReturn(data.get(0).get("OwnerEmail"));
    }
    @Given("The following Owner information table:")
    public void theFollowingOwnerInformationTable(DataTable dataTable) throws SQLException {
        List<Map<String,String>> data= dataTable.asMaps(String.class,String.class);
        setUpTableColumn(data);
    }


    @When("I choose to view the View Owner Information Details")
    public void iChooseToViewTheViewOwnerInformationDetails() throws SQLException {
        when( ownerConnecter.getOwnerInfo
                ("SELECT * FROM owner WHERE owner_username ='"+ownerUsername+"'")).thenReturn(resultSet);

        when(resultSet.next()).thenReturn(true,false);

    }

    @Then("I should see the Details  displayed on the screen")
    public void iShouldSeeTheDetailsDisplayedOnTheScreen() {
        tenantViewOwnerInfo.printOwnerInfo(ownerUsername);
    }
}
