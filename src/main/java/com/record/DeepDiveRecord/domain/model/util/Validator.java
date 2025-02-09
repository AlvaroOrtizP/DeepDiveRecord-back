package com.record.DeepDiveRecord.domain.model.util;

import java.math.BigDecimal;

public class Validator {
    public final static boolean validateInteger(Integer input) {
        if (input == null || input < 1) {
            return true;
        }
        return false;
    }

    public final static boolean validateString(String input) {
        if (input == null || input.isBlank()) {
            return true;
        }
        return false;
    }

    public final static boolean validateBigDecimal(BigDecimal input) {
        if (input == null) {
            return true;
        }
        return false;
    }

    public final static boolean validateDouble(Double input) {
        if (input == null) {
            return true;
        }
        return false;
    }

    public final static boolean validateHora(String input) {
        //TODO compronbar si los numeros son reales, es decir no puede venir 25:68
        String regex = "\\d{2}:\\d{2}";

        if (input == null || input.isBlank() || !input.contains(":")) {
            return true;
        } else {
            //Comprobamos que el valor es numerico
            if (!input.matches(regex)) {
                return true;
            }
            // Eliminar el ':' y verificar si los caracteres restantes son numéricos
            String numericPart = input.replace(":", "");
            if (!numericPart.chars().allMatch(Character::isDigit)) {
                return true;
            }
            Integer primer = Integer.valueOf(input.substring(0,2));
            Integer segun = Integer.valueOf(input.substring(3,5));
            if(primer<1 || primer>24){
                return true;
            }
            if(segun<-1 || segun>59){
                return true;
            }
        }
        return false;
    }
}
