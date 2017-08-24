package eu.gitcode.android.moneytalks.utils;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.EditText;

import eu.gitcode.android.moneytalks.R;

public final class UIUtils {

    private UIUtils() {
        throw new AssertionError();
    }

    public static void showFillError(Context context, EditText... editTexts) {
        boolean isFocused = false;
        for (EditText editText : editTexts) {
            if (StringUtils.isNullOrEmpty(editText.getText().toString())) {
                editText.setError(context.getString(R.string.field_cannot_be_empty));
                if (!isFocused) {
                    editText.requestFocus();
                    isFocused = true;
                }
            }
        }
    }

    public static boolean showFillError(Context context, TextInputEditText... textInputEditTexts) {
        boolean isFocused = false;
        for (TextInputEditText textInputEditText : textInputEditTexts) {
            if (textInputEditText != null && StringUtils.isNullOrEmpty(textInputEditText.getText().toString())) {
                textInputEditText.setError(context.getString(R.string.field_cannot_be_empty));
                textInputEditText.requestFocus();
                    isFocused = true;
            }
        }
        return isFocused;
    }

    public static void hideFieldsError(EditText... editTexts) {
        for (EditText editText : editTexts) {
            editText.setError(null);
        }
    }

    public static void hideFieldsError(TextInputLayout... textInputLayouts) {
        for (TextInputLayout textInputLayout : textInputLayouts) {
            textInputLayout.setErrorEnabled(false);
        }
    }

    public static void hideKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static void checkFirstUncheckRest(CompoundButton... radioButtons) {
        for (int i = 0; i < radioButtons.length; i++) {
            if (i == 0) {
                radioButtons[i].setChecked(true);
            } else {
                radioButtons[i].setChecked(false);
            }
        }
    }

    public static void changeViewsEnableStatus(boolean enable, View... views) {
        for (View view : views) {
            view.setEnabled(enable);
        }
    }
}
