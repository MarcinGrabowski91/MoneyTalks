package eu.gitcode.android.moneytalks.dagger.components;

import javax.inject.Singleton;

import dagger.Component;
import eu.gitcode.android.moneytalks.DebugMetricsHelper;
import eu.gitcode.android.moneytalks.application.App;
import eu.gitcode.android.moneytalks.controllers.AuthController;
import eu.gitcode.android.moneytalks.dagger.modules.AppModule;

@Singleton
@Component(
        modules = {AppModule.class}
)
public interface AppComponent {

    AuthController authController();

    DebugMetricsHelper debugMetricsHelper();

    void inject(App app);
}