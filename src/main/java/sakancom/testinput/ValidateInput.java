package sakancom.testinput;

import io.cucumber.core.logging.Logger;
import io.cucumber.core.logging.LoggerFactory;

public class ValidateInput {
    private static final Logger logger=  LoggerFactory.getLogger(ValidateInput.class);


    public boolean  itShouldValidateChoice(String input,int minNum,int maxNum){
        boolean isValid =false;
        try {
            int choice=Integer.parseInt(input);
            if(choice>=minNum && choice<=maxNum){
                isValid=true;
            }
            else  logger.error(()->"Invalid input . " +
                    "please Enter Number between "+minNum+" and "+maxNum);
        }
        catch (NumberFormatException e){
           logger.error(e,()->"Invalid Input ! , Make sure That you enter a number");
        }
        return isValid;
    }

    public boolean isNum(String input){
        for(char c:input.toCharArray()){
            if( !Character.isDigit(c)){
                return false;
            }
        }
        return true;
    }

}
