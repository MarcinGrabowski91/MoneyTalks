package eu.gitcode.android.moneytalks.ui.feature.budget.expenses.addedit;

import javax.inject.Inject;

import eu.gitcode.android.moneytalks.controllers.PreferenceController;
import eu.gitcode.android.moneytalks.dagger.scopes.FragmentScope;
import eu.gitcode.android.moneytalks.models.ui.Expense;
import eu.gitcode.android.moneytalks.ui.common.base.MvpBasePresenterRest;
import rx.subscriptions.CompositeSubscription;

@FragmentScope
public final class AddEditExpenseFragmentPresenter extends MvpBasePresenterRest<AddEditExpenseContract.View>
        implements AddEditExpenseContract.Presenter {

    private final PreferenceController preferenceController;

    private final CompositeSubscription subscriptions = new CompositeSubscription();

    private Expense expense;

    @Inject
    public AddEditExpenseFragmentPresenter(PreferenceController preferenceController) {
        this.preferenceController = preferenceController;
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        subscriptions.unsubscribe();
    }

    @Override
    public void handleExpenseData(Expense expense) {
        this.expense = expense;
        getView().showExpenseData(expense);
    }

    @Override
    public void addOrUpdateExpense() {
        getView().showAddOrUpdateSuccessView();
    }
}
