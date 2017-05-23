package eu.gitcode.android.moneytalks.ui.feature.main;

import dagger.Subcomponent;
import eu.gitcode.android.moneytalks.dagger.scopes.ActivityScope;

@ActivityScope
@Subcomponent
public interface MainComponent {

    void inject(MainActivity mainActivity);

    MainActivityPresenter getMainActivityPresenter();
}