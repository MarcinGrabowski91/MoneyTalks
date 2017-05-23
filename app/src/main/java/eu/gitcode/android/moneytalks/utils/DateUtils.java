package eu.gitcode.android.moneytalks.utils;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateUtils {

    public static final DateTimeFormatter ISO8601 = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    private DateUtils() {
        throw new AssertionError();
    }

    public static String getCurrentMonth() {
        DateTimeFormatter dtf = DateTimeFormat.forPattern("MMM yyyy");
        return dtf.print(DateTime.now());
    }

    public static String getDateStringFromDateTime(DateTime dateTime) {
        DateTimeFormatter dtf = DateTimeFormat.forPattern("dd MMM yyyy");
        return dtf.print(dateTime);
    }

    public static String getLongDateStringFromDateTime(DateTime dateTime) {
        DateTimeFormatter dtf = DateTimeFormat.forPattern("dd MMMM yyyy");
        return dtf.print(dateTime);
    }

    public static String getShortDateStringFromDateTime(DateTime dateTime) {
        DateTimeFormatter dtf = DateTimeFormat.forPattern("dd.MM.yy");
        return dtf.print(dateTime);
    }

    public static boolean isDatePassed(DateTime dateTime) {
        DateTime currentDateTime = new DateTime(DateTimeZone.UTC);
        return dateTime.isBefore(currentDateTime);
    }
}