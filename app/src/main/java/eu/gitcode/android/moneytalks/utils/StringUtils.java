package eu.gitcode.android.moneytalks.utils;

import java.util.regex.Pattern;

public final class StringUtils {

    private StringUtils() {
        throw new IllegalArgumentException("Utils class cannot have instance");
    }

    public static boolean isNullOrEmpty(String text) {
        return text == null || text.trim().isEmpty();
    }

    public static boolean isAnyNullOrEmpty(String... strings) {
        for (String string : strings) {
            if (isNullOrEmpty(string)) return true;
        }
        return false;
    }

    public static boolean isEmailValid(String email) {
        Pattern emailAddressPattern = Pattern.compile(
                "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                        "\\@" +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                        "(" +
                        "\\." +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                        ")+"
        );
        String trimmedEmail = email.trim();
        return emailAddressPattern.matcher(trimmedEmail).matches();
    }

    public static boolean isAnyStringEquals(String baseString, String... strings) {
        for (String string : strings) {
            if (baseString.equals(string)) return true;
        }
        return false;
    }
    public static Float stringToFloat(String value) {
        return StringUtils.isNullOrEmpty(value) ? null : Float.parseFloat(value);
    }

    public static String removeTooManySpace(String text) {
        return text.replaceAll("\\s+", " ");
    }
}
