package eu.gitcode.android.moneytalks.application;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private static final String SHARED_PREFERENCES_NAME = "eu.gitcode.android.moneytalks.shared.preferences";

    private final App app;

    public AppModule(App app) {
        this.app = app;
    }

    @Singleton
    @Provides
    App providesApplication() {
        return app;
    }

    @Singleton
    @Provides
    public Context provideContext() {
        return app.getApplicationContext();
    }

    @Singleton
    @Provides
    public SharedPreferences provideSharedPreferences(App app) {
        return app.getApplicationContext().
                getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    @Singleton
    @Provides
    public Resources provideResources(Context context) {
        return context.getResources();
    }
}