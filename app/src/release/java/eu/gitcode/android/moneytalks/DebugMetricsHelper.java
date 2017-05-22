package eu.gitcode.android.moneytalks;

import android.content.Context;

import net.hockeyapp.android.CrashManager;

import javax.inject.Inject;

public final class DebugMetricsHelper {

    @Inject
    public DebugMetricsHelper() {
        //no-op
    }

    public void init(Context context) {
        CrashManager.register(context);
    }
}
