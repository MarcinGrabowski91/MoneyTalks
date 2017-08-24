package eu.gitcode.android.moneytalks.ui.feature.budget.expenses.addedit;

import org.joda.time.DateTime;

import javax.inject.Inject;

import eu.gitcode.android.moneytalks.controllers.BudgetController;
import eu.gitcode.android.moneytalks.dagger.scopes.FragmentScope;
import eu.gitcode.android.moneytalks.models.request.TransactionRequest;
import eu.gitcode.android.moneytalks.models.ui.Transaction;
import eu.gitcode.android.moneytalks.ui.common.base.MvpBasePresenterRest;
import eu.gitcode.android.moneytalks.utils.RxTransformers;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

@FragmentScope
public final class AddEditExpenseFragmentPresenter extends MvpBasePresenterRest<AddEditExpenseContract.View>
        implements AddEditExpenseContract.Presenter {

    private final CompositeSubscription subscriptions = new CompositeSubscription();

    private BudgetController budgetController;

    private Transaction transaction;

    private Long subcategoryId;

    @Inject
    public AddEditExpenseFragmentPresenter(BudgetController budgetController) {
        this.budgetController = budgetController;
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        subscriptions.unsubscribe();
    }

    @Override
    public void handleExpenseData(Transaction transaction) {
        this.transaction = transaction;
        getView().showExpenseData(transaction);
    }

    @Override
    public void addOrUpdateExpense(String title, DateTime date, Float value) {
        TransactionRequest transactionRequest = TransactionRequest.builder()
                .name(title)
                .dateTime(date)
                .subcategoryMonthId(subcategoryId)
                .value(value)
                .build();
        subscriptions.add(budgetController.newTransaction(transactionRequest)
                .compose(RxTransformers.applyCompletableSchedulers())
                .subscribe(() -> getView().showAddOrUpdateSuccessView(),
                        throwable -> Timber.d("Error during adding or updating expense: %s",
                                throwable)));

    }

    @Override
    public void saveSubcategoryId(Long subcategoryId) {
        this.subcategoryId = subcategoryId;
    }
}
