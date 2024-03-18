package Sakancom.acceptance.AdminTest;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.mockito.Mockito;
import sakancom.admin.AdminViewReservation;
import sakancom.admin.requestservice.AdminHouseRequest;
import sakancom.admin.requestservice.AdminHouseRequestMenu;
import sakancom.db.RequestedHouseConnecter;
import sakancom.tenant.AdvertisementHousePrinter;
import sakancom.tenant.TenantAvailableHouse;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class AdminViewReservationFeatureStep {


        private static RequestedHouseConnecter requestedHouseConnecter;
        private static ResultSet resultSet;
        private static AdvertisementHousePrinter advertisementHousePrinter;// mock
        //--------------------------------------------------
        private static AdminViewReservation adminViewReservation; // new obj
        private static StringBuilder stringBuilder;
        @BeforeAll
        public static void before_all() throws SQLException {
            resultSet= Mockito.mock(ResultSet.class);
            requestedHouseConnecter=Mockito.mock(RequestedHouseConnecter.class);

            stringBuilder= new StringBuilder();
            advertisementHousePrinter=new AdvertisementHousePrinter(stringBuilder);

            adminViewReservation= new AdminViewReservation(requestedHouseConnecter,advertisementHousePrinter);
        }
        @AfterAll
        public static void after_all() throws Exception {}
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



        @Given("The following Reservation house:")
        public void theFollowingAvailableHouse(DataTable dataTable) throws SQLException {
            List<Map<String,String>> data= dataTable.asMaps(String.class,String.class);
            when(requestedHouseConnecter.getPendingRequest(
                    "SELECT * from requesthouse where Status = 1")).thenReturn(resultSet);
            when(resultSet.next()).thenReturn(true, false);
            setUpTableColumn(data);

        }

        @When("I choose to view the Reservation of the  House")
        public void iChooseToViewTheAvailableHouse() {
            adminViewReservation.getReservation();
        }


        @Then("I should see the Reservation displayed on the screen")
        public void iShouldSeeTheReservationDisplayedOnTheScreen() {
            assertTrue(  adminViewReservation.getIsPrinted());
        }


    }




