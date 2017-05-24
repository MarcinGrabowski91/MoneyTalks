package eu.gitcode.android.moneytalks.ui.feature.budget.expenses.show;

import dagger.Subcomponent;
import eu.gitcode.android.moneytalks.dagger.scopes.FragmentScope;

@FragmentScope
@Subcomponent
public interface ExpenseComponent {

    void inject(ExpenseFragment expenseFragment);

    ExpenseFragmentPresenter getExpensePresenter();
}