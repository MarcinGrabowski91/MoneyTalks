package eu.gitcode.android.moneytalks.ui.feature.budget.expenses.list;

import javax.inject.Inject;

import eu.gitcode.android.moneytalks.controllers.PreferenceController;
import eu.gitcode.android.moneytalks.dagger.scopes.FragmentScope;
import eu.gitcode.android.moneytalks.ui.common.base.MvpBasePresenterRest;
import rx.subscriptions.CompositeSubscription;

@FragmentScope
public final class ExpensesFragmentPresenter extends MvpBasePresenterRest<ExpensesContract.View>
        implements ExpensesContract.Presenter {

    private final PreferenceController preferenceController;

    private final CompositeSubscription subscriptions = new CompositeSubscription();

    @Inject
    public ExpensesFragmentPresenter(PreferenceController preferenceController) {
        this.preferenceController = preferenceController;
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        subscriptions.unsubscribe();
    }

    @Override
    public void handleBudgetData() {
        getView().showExpensesData();
    }
}
