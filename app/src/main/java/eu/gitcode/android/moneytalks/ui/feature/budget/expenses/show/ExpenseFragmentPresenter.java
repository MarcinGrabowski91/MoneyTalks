package eu.gitcode.android.moneytalks.ui.feature.budget.expenses.show;

import javax.inject.Inject;

import eu.gitcode.android.moneytalks.controllers.PreferenceController;
import eu.gitcode.android.moneytalks.dagger.scopes.FragmentScope;
import eu.gitcode.android.moneytalks.models.ui.Expense;
import eu.gitcode.android.moneytalks.ui.common.base.MvpBasePresenterRest;
import rx.subscriptions.CompositeSubscription;

@FragmentScope
public final class ExpenseFragmentPresenter extends MvpBasePresenterRest<ExpenseContract.View>
        implements ExpenseContract.Presenter {

    private final PreferenceController preferenceController;

    private final CompositeSubscription subscriptions = new CompositeSubscription();

    private Expense expense;

    @Inject
    public ExpenseFragmentPresenter(PreferenceController preferenceController) {
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
}
