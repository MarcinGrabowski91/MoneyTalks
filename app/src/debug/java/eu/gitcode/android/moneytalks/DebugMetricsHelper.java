package eu.gitcode.android.moneytalks;

import android.content.Context;

import com.frogermcs.androiddevmetrics.AndroidDevMetrics;
import com.squareup.leakcanary.LeakCanary;

import javax.inject.Inject;

import eu.gitcode.android.moneytalks.application.App;
import timber.log.Timber;

public final class DebugMetricsHelper {

    @Inject
    public DebugMetricsHelper() {
        //no-op
    }

    public void init(Context context) {
        // AndroidDevMetrics
        AndroidDevMetrics.initWith(context);

        // LeakCanary
        LeakCanary.install((App) context.getApplicationContext());

        // Timber
        Timber.plant(new Timber.DebugTree());
    }

}
