package Sakancom.acceptance;

import mainapplication.LoginManger;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.mockito.Mockito;
import sakancom.db.userconnection.UserDataBaseConnecter;
import sakancom.db.userconnection.UserInformationExistence;
import sakancom.user.User;
import sakancom.user.UserType;


import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class LoginUserFeatureSteps {

    private String userName;
    private String password;
    private boolean UserLoginStatus;
    private static  UserInformationExistence userInformationExistence;
    private static UserDataBaseConnecter userDataBaseConnecter;
    private static UserType userType;
    private static User theMockUser;
    private static LoginManger loginManger;
    @BeforeAll
    public static void before_all(){
         loginManger= new LoginManger();

        userInformationExistence =Mockito.mock(UserInformationExistence.class);
        userDataBaseConnecter=Mockito.mock(UserDataBaseConnecter.class);

        userType = Mockito.mock(UserType.class);
        loginManger.setUserType(userType);
        loginManger.setUserInformationExistence(userInformationExistence);
        loginManger.setUserDataBaseConnecter(userDataBaseConnecter);

        theMockUser=Mockito.mock(User.class);

    }
    @AfterAll
    public static void after_all() throws Exception {}

// ═════════════════════════════════════════════════════════════════════════════════════════════════════

    @When("the user enter a valid {string} and {string}")
    public void theUserEnterValidUsernameAndPassword(String userName, String password) {
        when(userInformationExistence.checkUserNameExistence(userName)).thenReturn(true);//isValidUsername
        when(userDataBaseConnecter.userConnector(userName, password)).thenReturn(theMockUser);
        doNothing().when(userType).determineType(theMockUser);
        loginManger.loginOperation(userName,password);
    }
    @Then("the user should be login to the system")
    public void theUserShouldBeLoginToTheSystem() {
        assertTrue  (loginManger.getIsLogin());
    }


// ═════════════════════════════════════════════════════════════════════════════════════════════════════
    @When("Enter an invalid {string} is entered")
    public void enterAnInvalidUsername(String userName) {
        String anyPassword = "xxx";
        when(userInformationExistence.checkUserNameExistence(userName)).thenReturn(false);//isValidUsername
        loginManger.loginOperation(userName, anyPassword);
    }
    @Then("an error message should be displayed")
    public void anErrorMessageShouldBeDisplayed() {
        assertTrue(loginManger.getIsErrorMsgPrinted());
    }
// ═════════════════════════════════════════════════════════════════════════════════════════════════════

    @When("user enter valid {string} and  invalid {string}")
    public void userEnterValidUsernameAndInvalidPassword(String userName, String password) {
        when(userInformationExistence.checkUserNameExistence(userName)).thenReturn(true);//isValidUsername
        when(userDataBaseConnecter.userConnector(userName,password))
                .thenReturn(null);// isValidPassword
        loginManger.loginOperation(userName,password);
    }
       // Then an error msg should be displayed : written already

// ═════════════════════════════════════════════════════════════════════════════════════════════════════
    @When("user enter blanks {string} and {string}")
    public void userEnterBlanksUsernameAndPassword(String userName, String password) {
        loginManger.loginOperation(userName,password);
    }
    // Then an error msg should be displayed : written already



}
