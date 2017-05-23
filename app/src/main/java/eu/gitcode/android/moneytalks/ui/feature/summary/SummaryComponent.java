package eu.gitcode.android.moneytalks.ui.feature.summary;

import dagger.Subcomponent;
import eu.gitcode.android.moneytalks.dagger.scopes.FragmentScope;

@FragmentScope
@Subcomponent
public interface SummaryComponent {

    void inject(SummaryFragment summaryFragment);

    SummaryFragmentPresenter getSummaryFragmentPresenter();
}