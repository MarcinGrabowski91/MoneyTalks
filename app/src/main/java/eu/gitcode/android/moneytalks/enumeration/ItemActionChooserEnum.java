package eu.gitcode.android.moneytalks.enumeration;

import android.content.res.Resources;
import android.support.annotation.StringRes;
import android.util.SparseArray;

import java.util.EnumSet;

import eu.gitcode.android.moneytalks.R;

public enum ItemActionChooserEnum {
    EDIT(R.string.edit),
    REMOVE(R.string.remove);

    private static final SparseArray<ItemActionChooserEnum> lookup = new SparseArray<>();
    private static final Integer[] namesArray = new Integer[ItemActionChooserEnum.values().length];

    static {
        int enumPosition = 0;
        for (ItemActionChooserEnum chooserEnum : EnumSet.allOf(ItemActionChooserEnum.class)) {
            lookup.put(chooserEnum.getStringRes(), chooserEnum);
            namesArray[enumPosition] = chooserEnum.getStringRes();
            enumPosition++;
        }
    }

    @StringRes
    private int code;

    ItemActionChooserEnum(@StringRes int code) {
        this.code = code;
    }

    public static ItemActionChooserEnum get(Integer code) {
        return lookup.get(code);
    }

    public static String[] getNamesResArray(Resources res) {
        String[] names = new String[namesArray.length];
        for (int i = 0; i < namesArray.length; i++) {
            names[i] = res.getString(namesArray[i]);
        }
        return names;
    }

    @StringRes
    public int getStringRes() {
        return code;
    }
}
