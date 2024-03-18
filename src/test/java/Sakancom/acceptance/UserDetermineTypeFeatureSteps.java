package Sakancom.acceptance;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.mockito.Mockito;
import sakancom.admin.Admin;
import sakancom.tenant.Tenant;
import sakancom.dashboard.AdminDashboard;
import sakancom.dashboard.TenantDashBoard;
import sakancom.user.User;
import sakancom.user.UserType;


import java.sql.SQLException;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;
import static sakancom.dashboard.DashboardStatus.DISPLAYED;

public class UserDetermineTypeFeatureSteps {
    private static User testingUser;
    private static UserType userType;


    private static Admin admin;
    private static AdminDashboard adminDashBoard;
    private static Tenant tenant;
    private static TenantDashBoard tenantDashBoard;

    @BeforeAll
    public static void before_all(){
        admin=Mockito.mock(Admin.class);
        adminDashBoard=Mockito.mock(AdminDashboard.class);

        tenant=Mockito.mock(Tenant.class);
        tenantDashBoard=Mockito.mock(TenantDashBoard.class);

        when(admin.getAdminDashBoard()).thenReturn(adminDashBoard);
        when(tenant.getTenantDashBoard()).thenReturn(tenantDashBoard);

        testingUser = new User("Khaled","200200","Admin");
        userType=new UserType();



    }
    @AfterAll
    public static void after_all() throws Exception {}



    @When("the User Type is Admin")
    public void theUserTypeIsAdmin()  {
        // Testing user here is a user has a Tenants Type :
        // adminDashBoard is Mocked !
        userType.setAdmin(admin);
       doNothing().when(adminDashBoard).adminMenu(testingUser);
        userType.determineType(testingUser);

    }
    @Then("the Admin DashBoard Should be Displayed")
    public void theAdminDashBoardShouldBeDisplayed() {
        assertSame(DISPLAYED,userType.getDashboardStatus() );
    }


    @When("the User Type is Tenant")
    public void theUserTypeIsTenant() {
        userType.setTenant(tenant);
        testingUser.setType("Tenant");
        doNothing().when(tenantDashBoard).tenantMenu(testingUser);
        userType.determineType(testingUser);
    }

    @Then("the Tenant DashBoard Should be Displayed")
    public void theTenantDashBoardShouldBeDisplayed() {
        assertSame( DISPLAYED,userType.getDashboardStatus());
    }
}
