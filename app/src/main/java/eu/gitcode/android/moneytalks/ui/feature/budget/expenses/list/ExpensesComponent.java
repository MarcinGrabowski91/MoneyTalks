package eu.gitcode.android.moneytalks.ui.feature.budget.expenses.list;

import dagger.Subcomponent;
import eu.gitcode.android.moneytalks.dagger.scopes.FragmentScope;

@FragmentScope
@Subcomponent
public interface ExpensesComponent {

    void inject(ExpensesFragment expensesFragment);

    ExpensesFragmentPresenter getExpensesPresenter();
}