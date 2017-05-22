package eu.gitcode.android.moneytalks.application;

import android.app.Application;
import android.content.Context;

import eu.gitcode.android.moneytalks.dagger.modules.AppModule;

public class App extends Application {

    private ApplicationComponent appComponent;

    public static ApplicationComponent getAppComponent(Context context) {
        return ((App) context.getApplicationContext()).appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent.getDebugMetricsHelper().init(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        // init dagger appComponent
        this.appComponent = DaggerApplicationComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
    }

}
