package com.airtribe.learntrack.util;

public class InputValidator {

    public static boolean isValidString(String input) {
        return input != null && !input.trim().isEmpty();
    }

    public static boolean isValidName(String name) {
        return isValidString(name) && name.matches("[a-zA-Z]+");
    }

    public static boolean isValidEmail(String email) {
        return email != null && email.contains("@");
    }

    public static boolean isIntValidChoice(int in, int...validChoices) {
        for (int vc : validChoices) {
            if (in == vc) {
                return true;
            }
        }

        return false;
    }

    public static boolean isStringValidChoice(String in, String...validChoices) {
        for (String vc : validChoices) {
            if (in.equalsIgnoreCase(vc)) {
                return true;
            }
        }

        return false;
    }
}
