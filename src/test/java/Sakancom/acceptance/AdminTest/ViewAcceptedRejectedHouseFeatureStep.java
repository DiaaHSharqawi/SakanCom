package Sakancom.acceptance.AdminTest;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.mockito.Mockito;
import sakancom.admin.HouseTablePrinter;
import sakancom.admin.requestservice.AdminHouseRequest;
import sakancom.admin.requestservice.AdminHouseRequestMenu;
import sakancom.db.RequestedHouseConnecter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class ViewAcceptedRejectedHouseFeatureStep {
    private static AdminHouseRequest adminHouseRequest;
    private static AdminHouseRequestMenu adminHouseRequestMenu;
    private static RequestedHouseConnecter requestedHouseConnecter;
    private static ResultSet resultSet;
    private static HouseTablePrinter houseTablePrinter;
    @BeforeAll
    public static void before_all() throws SQLException {
        resultSet= Mockito.mock(ResultSet.class);
        requestedHouseConnecter=Mockito.mock(RequestedHouseConnecter.class);

        //   adminHouseRequest.setRequestedHouseConnecter(requestedHouseConnecter);
        StringBuilder stringBuilder= new StringBuilder();
        houseTablePrinter= new HouseTablePrinter(stringBuilder);
        adminHouseRequest=new AdminHouseRequest(requestedHouseConnecter,houseTablePrinter);
        adminHouseRequestMenu=Mockito.mock(AdminHouseRequestMenu.class);
        doNothing().when(adminHouseRequestMenu).menuHandler();
        adminHouseRequest.setAdminHouseRequestMenu(adminHouseRequestMenu);

    }

    public void setUpTableColumn(List<Map<String,String>> data) throws SQLException {
        when(resultSet.getString(1)).thenReturn(data.get(0).get("RequestID"));
        when(resultSet.getString(2)).thenReturn(data.get(0).get("OwnerUserName"));
        when(resultSet.getString(3)).thenReturn(data.get(0).get("Name"));
        when(resultSet.getString(4)).thenReturn(data.get(0).get("City"));
        when(resultSet.getString(5)).thenReturn(data.get(0).get("Street"));
        when(resultSet.getString(6)).thenReturn(data.get(0).get("Description"));
        when(resultSet.getString(7)).thenReturn(data.get(0).get("ContactNumber"));
        when(resultSet.getString(8)).thenReturn(data.get(0).get("House For"));
        when(resultSet.getString(9)).thenReturn(data.get(0).get("R/month"));
        when(resultSet.getString(10)).thenReturn(data.get(0).get("Number of Room"));
        when(resultSet.getString(11)).thenReturn(data.get(0).get("Number of Bathroom"));
        when(resultSet.getString(12)).thenReturn(data.get(0).get("Area"));
        when(resultSet.getString(13)).thenReturn(data.get(0).get("Home Features"));
        when(resultSet.getString(14)).thenReturn(data.get(0).get("Image"));
        when(resultSet.getString(15)).thenReturn(data.get(0).get("Status"));
    }

    @When("I choose to view the Accepted table:")
    public void iChooseToViewTheAcceptedTable(DataTable dataTable) throws SQLException {
            List<Map<String,String>> data= dataTable.asMaps(String.class,String.class);
            int status=1;
        when(requestedHouseConnecter.
                getPendingRequest("SELECT * from requesthouse where Status="+status+"")).thenReturn(resultSet);
        when(adminHouseRequestMenu.getChoice() ).thenReturn(1,4);

        when(resultSet.next()).thenReturn(true,false);
        setUpTableColumn(data);

    }
    @Then("I should see the Accepted House table displayed on the screen")
    public void iShouldSeeTheAcceptedHouseTableDisplayedOnTheScreen() {
        adminHouseRequest.viewAcceptedRejected(true);

    }
//========================================================>
    @When("I choose to view the Rejected table:")
    public void iChooseToViewTheRejectedTable(DataTable dataTable) throws SQLException {
        List<Map<String,String>> data= dataTable.asMaps(String.class,String.class);

        when(resultSet.next()).thenReturn(true,false);
        int status=0;
        when(requestedHouseConnecter.
                getPendingRequest("SELECT * from requesthouse where Status="+status+"")).thenReturn(resultSet);
        setUpTableColumn(data);

    }

    @Then("I should see the Rejected House table displayed on the screen")
    public void iShouldSeeTheRejectedHouseTableDisplayedOnTheScreen() {
        adminHouseRequest.viewAcceptedRejected(false);
    }


}
