package eu.gitcode.android.moneytalks.ui.feature.notifications.logs;

import dagger.Subcomponent;
import eu.gitcode.android.moneytalks.dagger.scopes.FragmentScope;

@FragmentScope
@Subcomponent
public interface LogsComponent {

    void inject(LogsFragment logsFragment);

    LogsFragmentPresenter getLogsPresenter();
}