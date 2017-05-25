package eu.gitcode.android.moneytalks.enumeration;

import android.content.res.Resources;
import android.support.annotation.StringRes;
import android.util.SparseArray;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

import eu.gitcode.android.moneytalks.R;

public enum NotificationPagesEnum {
    MESSAGES(R.string.messages),
    LOGS(R.string.logs);

    private static final SparseArray<NotificationPagesEnum> lookup = new SparseArray<>();
    private static final Integer[] namesArray = new Integer[NotificationPagesEnum.values().length];

    static {
        int enumPosition = 0;
        for (NotificationPagesEnum enumValue : EnumSet.allOf(NotificationPagesEnum.class)) {
            lookup.put(enumValue.getStringRes(), enumValue);
            namesArray[enumPosition] = enumValue.getStringRes();
            enumPosition++;
        }
    }

    @StringRes
    private int code;

    NotificationPagesEnum(@StringRes int code) {
        this.code = code;
    }

    public static NotificationPagesEnum get(Integer code) {
        return lookup.get(code);
    }

    public static String[] getNamesResArray(Resources res) {
        String[] names = new String[namesArray.length];
        for (int i = 0; i < namesArray.length; i++) {
            names[i] = res.getString(namesArray[i]);
        }
        return names;
    }

    public static List<String> getNamesList(Resources res) {
        return Arrays.asList(getNamesResArray(res));
    }

    @StringRes
    public int getStringRes() {
        return code;
    }
}
