package mainapplication;

import io.cucumber.core.logging.Logger;
import io.cucumber.core.logging.LoggerFactory;
import sakancom.db.userconnection.UserDataBaseConnecter;
import sakancom.db.userconnection.UserInformationExistence;
import sakancom.user.User;
import sakancom.user.UserType;

import java.util.Scanner;

public class LoginManger {
    private static final Logger logger=  LoggerFactory.getLogger(LoginManger.class);

    private  Scanner scanner;
    private UserDataBaseConnecter userDataBaseConnecter ;
    private UserInformationExistence userInformationExistence;
    private UserType userType;
    private User fromDataBase;
    private boolean isLogin;

    public boolean getIsErrorMsgPrinted() {
        return isErrorMsgPrinted;
    }

    private boolean isErrorMsgPrinted;




    public boolean getIsLogin() {
        return isLogin;
    }



    public void setUserInformationExistence(UserInformationExistence userInformationExistence) {
        this.userInformationExistence = userInformationExistence;
    }
    public void setUserDataBaseConnecter(UserDataBaseConnecter userDataBaseConnecter) {
        this.userDataBaseConnecter = userDataBaseConnecter;}
    public void setUserType(UserType userType) {
        this.userType = userType;
    }



    public Scanner getScanner() {
        return scanner;
    }
    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

   public  boolean isNotBlank(String userName,String password){
        return  ! (userName.isBlank() || password.isBlank());
   }
   public boolean isValidUsername(String userName){
      return userInformationExistence.checkUserNameExistence(userName);
   }
   public boolean isValidPassword(String userName,String password){
        fromDataBase = userDataBaseConnecter.userConnector(userName, password);
        return  fromDataBase !=null;
   }

    public  void loginOperation(String userName,String password)  {
        isLogin=false;
        if  (isNotBlank(userName,password))
        {
            if (isValidUsername(userName))
            {
                if (isValidPassword(userName,password)) {
                    isLogin = true;
                   logger.info(()->"The User Login Successfully !");
                    userType.determineType(fromDataBase);

                }
                else {
                    logger.error(()->"Invalid password !");
                    isErrorMsgPrinted = true;
                }
            }
            else
            {
               logger.error(()->"Invalid Username ! ");
                isErrorMsgPrinted = true;
            }
        }
        else {
            logger.error(()->"Invalid Input Login with Blank field !");
            isErrorMsgPrinted = true;
        }
    }

    public  String readInput(String msg){
        logger.info(()->msg);
        return scanner.next();
    }
}
