package mainapplication;

import sakancom.db.userconnection.UserDataBaseConnecter;
import sakancom.db.userconnection.UserInformationExistence;
import sakancom.user.UserType;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// This class control the Login to system process
public class Main {
    private static final Logger logger=  LogManager.getLogger(Main.class);
    public static void main (String []args)  {

        UserInformationExistence userInformationExistence;
        String exit;
        Scanner scanner;
        scanner=new Scanner(System.in);
        userInformationExistence=new UserInformationExistence();
        UserDataBaseConnecter userDataBaseConnecter= new UserDataBaseConnecter();
        UserType userType= new UserType();

        LoginManger loginManger= new LoginManger();
        loginManger.setScanner(scanner);
        loginManger.setUserDataBaseConnecter(userDataBaseConnecter);
        loginManger.setUserInformationExistence(userInformationExistence);
        loginManger.setUserType(userType);
        boolean isLogOut=false;

        while (!isLogOut) {
            String loginForm =
                    "\n╔══════════════════════╗\n" +
                            "║ Welcome to Sakancom  ║\n" +
                            "╠══════════════════════╣\n" +
                            "║ Username: [________] ║\n" +
                            "║ Password: [________] ║\n" +
                            "╠══════════════════════╣\n" +
                            "║    [ Login ]         ║\n" +
                            "╚══════════════════════╝";
            logger.info(loginForm);
            String userName = loginManger.readInput("Enter username :");
            String password = loginManger.readInput("Enter password :");
            loginManger.loginOperation(userName, password);
            logger.info(()->"Did u want to out ? [y/n] : ");
            exit=scanner.next();
            if(exit.equalsIgnoreCase("y"))
                isLogOut=true;
            else
                logger.error(()->"Invalid input");
        }

    }

}

