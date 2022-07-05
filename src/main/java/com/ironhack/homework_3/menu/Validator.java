package com.ironhack.homework_3.menu;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Service;

public class Validator {

    public static boolean isPhoneNumberValid(String phoneNumber){
        if (phoneNumber.length() > 1 && phoneNumber.length() < 16 && phoneNumber.matches("[0-9()+-]+")) {
            return true;
        } else {
            throw new IllegalArgumentException("The phone number must have a maximum of 16 digits including prefix.");
        }
    }

    public static boolean isEmailValid(String email){
        boolean result = false;
        if(EmailValidator.getInstance().isValid(email)){
            result = true;
        } else {
            throw new IllegalArgumentException("The email address format is not valid.");
        }
        return result;
    }

    public static boolean isStringValid(String string) {
        if(string.length() > 1 && string.length() < 35 && string.replaceAll("\\s+","").matches("[.áéíóúàèìòùäëïöüãẽõçłña-zÁÉÍÓÚÀÈÌÒÙÄËÏÖÜÃẼÕÇŁÑA-Z]+")){
            return true;
        } else {
            throw new IllegalArgumentException("Not a valid input.");
        }
    }

    public static boolean isAnswerYesOrNoValid(String string) {
        if(string.length() > 0 && string.length() < 4 && string.replaceAll("\\s+","").matches("[YESNO]+")){
            return true;
        } else {
            throw new IllegalArgumentException("Invalid response.");
        }
    }

}
