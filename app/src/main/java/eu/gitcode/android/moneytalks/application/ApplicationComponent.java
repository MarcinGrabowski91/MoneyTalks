package eu.gitcode.android.moneytalks.application;


import javax.inject.Singleton;

import dagger.Component;
import eu.gitcode.android.moneytalks.DebugMetricsHelper;

@Singleton
@Component
public interface ApplicationComponent {
    DebugMetricsHelper getDebugMetricsHelper();
}
