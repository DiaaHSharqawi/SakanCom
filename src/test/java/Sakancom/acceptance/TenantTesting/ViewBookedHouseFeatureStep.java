package Sakancom.acceptance.TenantTesting;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.mockito.Mockito;
import sakancom.db.BookingHouseConnecter;
import sakancom.tenant.BookedHouseTablePrinter;
import sakancom.tenant.TenantBookedHouse;
import sakancom.tenant.TenantViewInfo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

public class ViewBookedHouseFeatureStep {
        private static TenantBookedHouse tenantBookedHouse;
        private static BookingHouseConnecter bookingHouseConnecter;
        private static BookedHouseTablePrinter bookedHouseTablePrinter;

        private static ResultSet resultSet;
        private String tenantUsername;
        private String ownerUsername;
        private static StringBuilder  stringBuilder;
        //--------------------------------------------------
        private static TenantViewInfo tenantViewInfo; // new obj
        @BeforeAll
        public static void before_all() throws SQLException {
            resultSet= Mockito.mock(ResultSet.class);
            bookingHouseConnecter=Mockito.mock(BookingHouseConnecter.class);

            stringBuilder=new StringBuilder();
            bookedHouseTablePrinter=new BookedHouseTablePrinter(stringBuilder);

            tenantBookedHouse=new TenantBookedHouse(bookingHouseConnecter,bookedHouseTablePrinter);
        }
        @AfterAll
        public static void after_all() throws Exception {}

        public void setUpTableColumn(List<Map<String,String>> data) throws SQLException {
            this.tenantUsername=data.get(0).get("tenantName");
            this.ownerUsername=data.get(0).get("ownerName");
            when(resultSet.getString(1)).thenReturn(data.get(0).get("BookingID"));
            when(resultSet.getString(2)).thenReturn(data.get(0).get("tenantName"));
            when(resultSet.getString(3)).thenReturn(data.get(0).get("ownerName"));
            when(resultSet.getString(4)).thenReturn(data.get(0).get("BookingDate"));
        }



        @Given("The following Booked House Inforamation :")
        public void theFollowingBookingHouseTable(DataTable dataTable) throws SQLException {
            List<Map<String,String>> data= dataTable.asMaps(String.class,String.class);
            setUpTableColumn(data);
        }

        @When("I choose to view the Booked House")
        public void iChooseToViewTheBookedHouse() throws SQLException {
            when(bookingHouseConnecter.getBookedHouse(tenantUsername,ownerUsername)).thenReturn(resultSet);
            when(resultSet.next()).thenReturn(true,false);
            tenantBookedHouse.getBookedHouse(tenantUsername,ownerUsername);
        }


        @Then("I should see the Booked Details  displayed on the screen")
        public void iShouldSeeTheBookedDetailsDisplayedOnTheScreen() {
            assertTrue(  tenantBookedHouse.getIsPrinted());
        }

    }


