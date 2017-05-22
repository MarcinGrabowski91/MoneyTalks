package eu.gitcode.android.moneytalks.application;


import javax.inject.Singleton;

import dagger.Component;
import eu.gitcode.android.moneytalks.DebugMetricsHelper;
import eu.gitcode.android.moneytalks.controllers.AuthController;
import eu.gitcode.android.moneytalks.controllers.PreferenceController;
import eu.gitcode.android.moneytalks.dagger.modules.AppModule;
import eu.gitcode.android.moneytalks.ui.feature.login.LoginComponent;
import eu.gitcode.android.moneytalks.ui.feature.register.RegisterComponent;

@Singleton
@Component(
        modules = {AppModule.class}
)

public interface ApplicationComponent {
    DebugMetricsHelper getDebugMetricsHelper();

    PreferenceController getPreferenceController();

    AuthController getAuthController();

    LoginComponent getLoginFragmentComponent();

    RegisterComponent getRegisterComponent();

    void inject(App app);
}
