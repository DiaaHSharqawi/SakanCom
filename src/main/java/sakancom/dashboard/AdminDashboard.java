package sakancom.dashboard;


import io.cucumber.core.logging.Logger;
import io.cucumber.core.logging.LoggerFactory;
import sakancom.admin.AdminViewReservation;
import sakancom.admin.requestservice.AdminHouseRequest;
import sakancom.testinput.ValidateInput;
import sakancom.user.User;

import java.util.Scanner;

public class AdminDashboard {
    private static final Logger logger=  LoggerFactory.getLogger(AdminDashboard.class);
    private final AdminHouseRequest adminHouseRequest;
    private final Scanner scanner;
    private  ValidateInput validateInput;
    private int choice;
    private final AdminViewReservation adminViewReservation;
    public AdminDashboard(Scanner scanner,
                          AdminHouseRequest adminHouseRequest,
                          AdminViewReservation adminViewReservation) {
        this.adminHouseRequest = adminHouseRequest;
        this.scanner = scanner;
        this.adminViewReservation=adminViewReservation;
    }
    public void setValidateInput(ValidateInput validateInput){
        this.validateInput=validateInput;
    }

    public void adminMenu(User admin)  {
            while (choice!=5) {
                adminDashboardTask( admin);
                clearScreen();
            }
    }

    public  void adminDashboardTask(User admin)  {
        // Here there is a adminHouseRequest which handel
        String menu =
                "\n"+
                "\t\t====================================================\n" +
                "\t\t||   Housing Management System - Administrator    ||\n" +
                "\t\t ===================================================\n" +
                "\t\t||   Welcome Back , " + admin.getUserName() + "                         ||\n"+
                "\t\t----------------------------------------------------\n" +
                "\t\t||\t1. view Housing Request "+"                      ||\n" +
                "\t\t||\t2. View Accepted Request \t\t              ||\n" +
                "\t\t||\t3. View Rejected Request  \t\t              ||\n" +
                "\t\t||\t4. View Reservations   \t\t                  ||\n" +
                "\t\t||\t5. Logout\t\t\t\t                      ||\n" +
                "\t\t====================================================\n" +
                "Enter choice: ";

         logger.info(()->menu);
         String input=scanner.next();
         scanner.nextLine();
         boolean isValidChoice= validateInput.itShouldValidateChoice(input,1,5);
            if(isValidChoice) {
                    choice=Integer.parseInt(input);
                    switch (choice) {
                        case 1:
                            adminHouseRequest.viewRequestedHouse();
                            break;
                        case 2:
                            adminHouseRequest.viewAcceptedRejected(true);
                            break;
                        case 3:
                            adminHouseRequest.viewAcceptedRejected(false);
                            break;
                        case 4:
                            adminViewReservation.getReservation();
                            break;
                        default:
                        break;
                    }

        }

    }



    public void clearScreen(){
        String clear="\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";
        logger.info(()->clear);
    }
}

