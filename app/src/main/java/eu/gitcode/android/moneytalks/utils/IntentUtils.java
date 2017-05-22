package eu.gitcode.android.moneytalks.utils;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

public class IntentUtils {

    private IntentUtils() {
        throw new AssertionError();
    }

    public static Intent getAppSettingsIntent(Context context) {
        Intent appSettingsIntent = new Intent();
        appSettingsIntent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                .addCategory(Intent.CATEGORY_DEFAULT)
                .setData(Uri.parse("package:" + context.getPackageName()));
        return appSettingsIntent;
    }

    public static boolean isIntentCallable(Context context, Intent intent) {
        return intent.resolveActivity(context.getPackageManager()) != null;
    }
}