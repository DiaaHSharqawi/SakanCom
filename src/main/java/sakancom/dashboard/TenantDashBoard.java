package sakancom.dashboard;

import io.cucumber.core.logging.Logger;
import io.cucumber.core.logging.LoggerFactory;
import sakancom.tenant.TenantAvailableHouse;
import sakancom.tenant.TenantBookAccommodation;
import sakancom.testinput.ValidateInput;
import sakancom.user.User;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TenantDashBoard {

    private static final Logger logger=  LoggerFactory.getLogger(TenantDashBoard.class);

    private final TenantBookAccommodation tenantBookAccommodation; // injected
        private final TenantAvailableHouse tenantAvailableHouse; // injected
    //------------------------------------------->
        private Scanner scanner; // injected by setter
        private ValidateInput validateInput; // injected ny setter
        //--------------------------------------------->
        private int choice;
        private int accomID;

        public TenantDashBoard(TenantAvailableHouse tenantAvailableHouse,
                                TenantBookAccommodation tenantBookAccommodation) {
            this.tenantAvailableHouse = tenantAvailableHouse;
            this.tenantBookAccommodation=tenantBookAccommodation;
        }

        public void setValidateInput(ValidateInput validateInput){
            this.validateInput=validateInput;
        }
        public void setScanner(Scanner scanner) {this.scanner = scanner;}




        public void tenantMenu(User tenant)  {
            while (choice!=2) {
                tenantDashBoard( tenant);
                clearScreen();
            }
        }


    public void readAccomID(){
        //-----------------
        logger.info(()->"\nEnter Request ID :");
        try {
            accomID =scanner.nextInt();
        } catch (InputMismatchException e) {
            logger.info(e,()->"Invalid Input !");
        }
    }

        public  void tenantDashBoard(User tenant)  {
            // Here there is a adminHouseRequest which handel
            tenantAvailableHouse.getAvailableHouse();
            String menu =   "\n"+
                            "\t\t====================================================\n" +
                            "\t\t||   Housing Management System - Tenant Menu      ||\n" +
                            "\t\t ===================================================\n" +
                            "\t\t||   Welcome Back , " + tenant.getUserName() + "                        ||\n"+
                            "\t\t----------------------------------------------------\n" +
                            "\t\t||\t1. Book Accommodation \t\t                  ||\n" +
                            "\t\t||\t2. Logout\t\t\t\t                      ||\n" +
                            "\t\t====================================================\n" +
                            "Enter choice: ";

            logger.info(()->menu);
            String input=scanner.next();
            boolean isValidChoice= validateInput.itShouldValidateChoice(input,1,2);
            if(isValidChoice) {
                choice=Integer.parseInt(input);
                if (choice == 1) {
                    scanner.nextLine();
                    readAccomID();
                    tenantBookAccommodation.bookAccommodation(accomID, tenant.getUserName());
                }

            }

        }


        public void clearScreen(){
            String clear="\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";
            logger.info(()->clear);
        }
    }
