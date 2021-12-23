package com.example.electricconsumptioncalculator;

public class ValidationUtility {

    public static boolean isNumber(String pValue){
        if(pValue.matches("\\d+(?:\\.\\d+)?"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static Boolean isStringEmpty(String pValue){
        if(pValue != null && !pValue.isEmpty())
            return false;
        else
            return true;
    }

    public static boolean isValidConsumerID(String pValue) {
        if(!isStringEmpty(pValue)){
            if(pValue.length() == 10 && isAlphaNumeric(pValue)){
                return true;
            }
        }

        return false;
    }

    public static boolean isAlphaNumeric(String pValue) {
        return pValue != null && pValue.matches("^[a-zA-Z0-9]*$");
    }
}
