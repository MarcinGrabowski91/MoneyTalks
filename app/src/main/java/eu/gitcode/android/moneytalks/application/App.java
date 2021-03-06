package eu.gitcode.android.moneytalks.application;

import android.app.Application;
import android.content.Context;

import net.danlew.android.joda.JodaTimeAndroid;

import eu.gitcode.android.moneytalks.BuildConfig;
import eu.gitcode.android.moneytalks.api.ApiModule;

public class App extends Application {

    private ApplicationComponent appComponent;

    public static ApplicationComponent getAppComponent(Context context) {
        return ((App) context.getApplicationContext()).appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent.getDebugMetricsHelper().init(this);
        JodaTimeAndroid.init(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        this.appComponent = DaggerApplicationComponent
                .builder()
                .appModule(new AppModule(this))
                .apiModule(new ApiModule(BuildConfig.BASE_URL))
                .build();
    }

}
