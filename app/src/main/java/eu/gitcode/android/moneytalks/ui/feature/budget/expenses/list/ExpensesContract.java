package eu.gitcode.android.moneytalks.ui.feature.budget.expenses.list;

import eu.gitcode.android.moneytalks.models.ui.Transaction;
import eu.gitcode.android.moneytalks.ui.common.base.MvpPresenterRest;
import eu.gitcode.android.moneytalks.ui.common.base.MvpViewRest;

public interface ExpensesContract {
    interface View extends MvpViewRest {
        void showExpensesData();

        void showRemoveSuccessView();
    }

    interface Presenter extends MvpPresenterRest<View> {
        void handleBudgetData();

        void handleRemoveExpense(Transaction transaction);
    }
}