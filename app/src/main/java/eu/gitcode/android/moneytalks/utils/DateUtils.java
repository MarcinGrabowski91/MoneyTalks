package eu.gitcode.android.moneytalks.utils;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Locale;

public class DateUtils {

    public static final DateTimeFormatter ISO8601 = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    private DateUtils() {
        throw new AssertionError();
    }

    public static String getCurrentMonthWithYear() {
        DateTimeFormatter dtf = DateTimeFormat.forPattern("MMM yyyy").withLocale(Locale.US);
        return dtf.print(DateTime.now());
    }

    public static String getMonthWithYear(DateTime dateTime) {
        DateTimeFormatter dtf = DateTimeFormat.forPattern("MMM yyyy").withLocale(Locale.US);
        return dtf.print(dateTime);
    }

    public static String getDateStringFromDateTime(DateTime dateTime) {
        DateTimeFormatter dtf = DateTimeFormat.forPattern("dd MMM yyyy").withLocale(Locale.US);
        return dtf.print(dateTime);
    }

    public static String getLongDateStringFromDateTime(DateTime dateTime) {
        DateTimeFormatter dtf = DateTimeFormat.forPattern("dd MMMM yyyy").withLocale(Locale.US);
        return dtf.print(dateTime);
    }

    public static String getShortDateStringFromDateTime(DateTime dateTime) {
        DateTimeFormatter dtf = DateTimeFormat.forPattern("dd.MM.yy").withLocale(Locale.US);
        return dtf.print(dateTime);
    }

    public static boolean isDatePassed(DateTime dateTime) {
        DateTime currentDateTime = new DateTime(DateTimeZone.UTC);
        return dateTime.isBefore(currentDateTime);
    }
}