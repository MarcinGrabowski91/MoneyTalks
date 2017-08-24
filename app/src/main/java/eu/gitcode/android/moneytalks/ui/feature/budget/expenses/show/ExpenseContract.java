package eu.gitcode.android.moneytalks.ui.feature.budget.expenses.show;

import eu.gitcode.android.moneytalks.models.ui.Transaction;
import eu.gitcode.android.moneytalks.ui.common.base.MvpPresenterRest;
import eu.gitcode.android.moneytalks.ui.common.base.MvpViewRest;

public interface ExpenseContract {
    interface View extends MvpViewRest {
        void showExpenseData(Transaction transaction);

        void showRemoveSuccessView();
    }

    interface Presenter extends MvpPresenterRest<View> {
        Transaction getExpense();

        void handleExpenseData(Transaction transaction);

        void handleRemoveExpense();
    }
}