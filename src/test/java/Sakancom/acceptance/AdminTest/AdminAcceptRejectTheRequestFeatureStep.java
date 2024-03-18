package Sakancom.acceptance.AdminTest;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.mockito.Mockito;
import sakancom.admin.requestservice.AdminAcceptRejectRequest;
import sakancom.db.RequestedHouseConnecter;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;


public class AdminAcceptRejectTheRequestFeatureStep {
    private static AdminAcceptRejectRequest adminAcceptRejectRequest;
    private static RequestedHouseConnecter requestedHouseConnecter;
    private static ResultSet resultSet;
    private int requestId;
    private boolean isTheRequestIdExist;
    private String action;


    @BeforeAll
    public static void before_all(){
        requestedHouseConnecter=Mockito.mock(RequestedHouseConnecter.class);
        resultSet=Mockito.mock(ResultSet.class);
        adminAcceptRejectRequest=new AdminAcceptRejectRequest(requestedHouseConnecter);
    }
    @AfterAll
    public static void after_all() throws Exception {}
// ═════════════════════════════════════════════════════════════════════════════════════════════════════
    @Given("a request with ID {string}")
    public void givenARequestWithID(String requestId) {
        this.requestId = Integer.parseInt(requestId);
    }

    public void setUpIsRequestIDExistence(boolean isExist){
        when(requestedHouseConnecter.getPendingRequest
                ("select RequestID from requesthouse where RequestID='"+requestId+"'")).thenReturn(resultSet);
    }

    @When("the user checks if the request exists")
    public void whenUserChecksIfRequestExists()  {
      setUpIsRequestIDExistence(true);
    }

    @Then("the request with ID {string} should be {string}")
    public void theRequestWithIDSShouldBeExistence(String requestID, String existence) throws SQLException {
        if(existence.equals("Yes")){
            when(resultSet.next()).thenReturn(true);
            isTheRequestIdExist =  adminAcceptRejectRequest.isRequestIdExistence(requestId);
            assertTrue(isTheRequestIdExist);
        }
        else{
            when(resultSet.next()).thenReturn(false);
            isTheRequestIdExist =  adminAcceptRejectRequest.isRequestIdExistence(requestId);
            assertFalse(isTheRequestIdExist);
        }

    }

    public void setUpMockIsAcceptedRejectedMethod(int status) throws SQLException {
        when(requestedHouseConnecter.getPendingRequest
                ("select Status from requesthouse WHERE RequestID="+requestId+"")).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);



            when(resultSet.getInt("Status")).thenReturn(status);



        doNothing().when(requestedHouseConnecter).setStatusRequest(requestId,status);
    }


    @And("the user chooses to {string}")
    public void whenUserChoosesToAcceptOrRejectRequest(String action) throws SQLException {
        this.action = action;

        if (isTheRequestIdExist) {
            if (action.equals("Accept")) {
                // for isAcceptedRejected
                setUpMockIsAcceptedRejectedMethod(1);
                adminAcceptRejectRequest.acceptRequest(requestId);
            } else if (action.equals("Reject")) {
                setUpMockIsAcceptedRejectedMethod(0); // true mean that
                adminAcceptRejectRequest.rejectRequest(requestId);
            }
        }


        else
        {
            action="";
        }
    }

    @Then("the request should be {string}")
    public void thenTheRequestShouldBe( String expectedResult) {

        if(action.equals("Accept"))
            assertEquals (    expectedResult.equals("Accepted"),
                    adminAcceptRejectRequest.getIsRequestAcceptedRejectedSuccessfully());

        else if (action.equals("Reject")) {
            assertEquals(expectedResult.equals("Rejected"),
                    adminAcceptRejectRequest.getIsRequestAcceptedRejectedSuccessfully());
        }
        else{
            assertEquals(expectedResult.equals("Not Found"),
                        action.equals(""));
            System.out.println("Not Found !");
        }
        }



}


