package eu.gitcode.android.moneytalks.application;


import javax.inject.Singleton;

import dagger.Component;
import eu.gitcode.android.moneytalks.DebugMetricsHelper;
import eu.gitcode.android.moneytalks.controllers.LoginController;
import eu.gitcode.android.moneytalks.controllers.PreferenceController;
import eu.gitcode.android.moneytalks.dagger.modules.AppModule;
import eu.gitcode.android.moneytalks.ui.feature.login.LoginComponent;

@Singleton
@Component(
        modules = {AppModule.class}
)

public interface ApplicationComponent {
    DebugMetricsHelper getDebugMetricsHelper();

    PreferenceController getPreferenceController();

    LoginController getLoginController();

    LoginComponent getLoginFragmentComponent();

    void inject(App app);
}
