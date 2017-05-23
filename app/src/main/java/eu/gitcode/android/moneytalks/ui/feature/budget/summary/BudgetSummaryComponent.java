package eu.gitcode.android.moneytalks.ui.feature.budget.summary;

import dagger.Subcomponent;
import eu.gitcode.android.moneytalks.dagger.scopes.FragmentScope;

@FragmentScope
@Subcomponent
public interface BudgetSummaryComponent {

    void inject(BudgetSummaryFragment budgetSummaryFragment);

    BudgetSummaryFragmentPresenter getBudgetSummaryFragmentPresenter();
}