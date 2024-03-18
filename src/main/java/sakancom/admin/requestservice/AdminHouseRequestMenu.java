package sakancom.admin.requestservice;

import io.cucumber.core.logging.Logger;
import io.cucumber.core.logging.LoggerFactory;

import sakancom.testinput.ValidateInput;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AdminHouseRequestMenu {
    private static final Logger logger=  LoggerFactory.getLogger(AdminHouseRequestMenu.class);

    private ValidateInput validateInput;
    private final AdminAcceptRejectRequest adminAcceptRejectRequest;
    private final AdminViewOwner viewOwner;
    private boolean isExist;

    public void setChoice(int choice) {
        this.choice = choice;
    }

    private int choice;
    private int reqID;
    private Scanner scanner;

    public int getChoice() {
        return choice;
    }
    public void setScanner(Scanner scanner){
         this.scanner=scanner;
     }

    public void setValidateInput(ValidateInput validateInput) {
         this.validateInput=validateInput;
    }

    public AdminHouseRequestMenu(AdminAcceptRejectRequest adminAcceptRejectRequest, AdminViewOwner viewOwner) {
        this.adminAcceptRejectRequest=adminAcceptRejectRequest;
        this.viewOwner = viewOwner;
    }

    public void readReqID(){
         //-----------------
            logger.info(()->"Enter Request ID :");
            try {
                reqID=scanner.nextInt();
            } catch (InputMismatchException e) {
                logger.error(()->"Invalid Input !");
            }
    }


    public String readChoice(){
         return scanner.next();
    }


    public void handelTheAdminAcceptOperation() throws SQLException {
        readReqID();

      boolean  isNum=validateInput.isNum(Integer.toString(reqID));
        if(isNum) {
            isExist = adminAcceptRejectRequest.isRequestIdExistence(reqID);
            if (isExist) {
                adminAcceptRejectRequest.acceptRequest(reqID);

            } else
                logger.error(()->"Request ID not found !");
        }
    }

    public void handelTheAdminRejectOperation() throws SQLException {
        readReqID();
      boolean  isNum=validateInput.isNum(Integer.toString(reqID));
        if(isNum) {
            isExist = adminAcceptRejectRequest.isRequestIdExistence(reqID);
            if (isExist) {
                adminAcceptRejectRequest.rejectRequest(reqID);
            } else
                logger.error(()->"Request ID not found !");
        }
    }


    public void menuHandler() throws SQLException {
         isExist=false;
            while (true) {
                printMenu();
                String input = readChoice();
                scanner.nextLine();
                boolean isValidInput = validateInput.itShouldValidateChoice(input, 1, 4);
                if (isValidInput) {
                    choice = Integer.parseInt(input);
                    switch (choice) {
                        case 1:
                            handelTheAdminAcceptOperation();
                            break;
                        case 2:
                            handelTheAdminRejectOperation();
                            break;
                        case 3:
                            viewOwner.adminPrintOwnerTable();
                            break;
                        default:
                            return;
                    }
                }
                break;
            }


    }

    public void printMenu(){
        String menu=
             "\n"  +"╭───────────────────────────╮\n"
                   +"│            Menu           │\n"
                   +"├───────────────────────────┤\n"
                   +"│ 1. Accept Request         │\n"
                   +"│ 2. Reject Request         │\n"
                   +"│ 3. View Owner Information │\n"
                   +"│ 4. Exit                   │\n"
                   +"╰───────────────────────────╯\n"
                   +"Enter your choice : ";
        logger.info(()->menu);

    }
}
