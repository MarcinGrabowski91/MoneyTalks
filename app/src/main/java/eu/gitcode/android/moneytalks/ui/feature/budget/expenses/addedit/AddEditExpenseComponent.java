package eu.gitcode.android.moneytalks.ui.feature.budget.expenses.addedit;

import dagger.Subcomponent;
import eu.gitcode.android.moneytalks.dagger.scopes.FragmentScope;

@FragmentScope
@Subcomponent
public interface AddEditExpenseComponent {

    void inject(AddEditExpenseFragment addEditExpenseFragment);

    AddEditExpenseFragmentPresenter getAddEditExpensePresenter();
}