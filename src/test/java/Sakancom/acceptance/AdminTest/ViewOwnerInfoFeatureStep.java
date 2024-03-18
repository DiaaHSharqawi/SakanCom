package Sakancom.acceptance.AdminTest;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.mockito.Mockito;
import sakancom.admin.requestservice.AdminViewOwner;
import sakancom.db.OwnerConnecter;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.when;


public class ViewOwnerInfoFeatureStep {

    private static AdminViewOwner viewOwner;
    private static OwnerConnecter ownerConnecter;
    private static ResultSet resultSet;

    @BeforeAll
    public static void before_all(){
        StringBuilder stringBuilder=new StringBuilder();
        ownerConnecter = Mockito.mock(OwnerConnecter.class);
        resultSet=Mockito.mock(ResultSet.class);
        viewOwner = new AdminViewOwner(ownerConnecter,stringBuilder);


    }
    @AfterAll
    public static void after_all() throws Exception {}

    @When("I print the owner table {string} with {string}, {string}, and {string}")
    public void iPrintTheOwnerTable(String ownerUsername, String ownerName, String ownerPhone, String ownerEmail) throws SQLException {
        when(  ownerConnecter.
                getOwnerInfo("SELECT * FROM owner join requesthouse on owner.owner_username=requesthouse.OwnerUserName " +
                        "where requesthouse.Status=-1 ")).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true,false);
        when(resultSet.getString(1)).thenReturn(ownerUsername);
        when(resultSet.getString(2)).thenReturn(ownerName);
        when(resultSet.getString(3)).thenReturn(ownerPhone);
        when(resultSet.getString(4)).thenReturn(ownerEmail);

    }
    @Then("I should see the owner information displayed on the screen")
    public void iShouldSeeTheOwnerInformationDisplayedOnTheScreen() throws SQLException {
        viewOwner.adminPrintOwnerTable();




    }



}
