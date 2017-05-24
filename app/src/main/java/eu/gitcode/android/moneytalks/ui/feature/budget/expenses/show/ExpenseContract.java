package eu.gitcode.android.moneytalks.ui.feature.budget.expenses.show;

import eu.gitcode.android.moneytalks.models.ui.Expense;
import eu.gitcode.android.moneytalks.ui.common.base.MvpPresenterRest;
import eu.gitcode.android.moneytalks.ui.common.base.MvpViewRest;

public interface ExpenseContract {
    interface View extends MvpViewRest {
        void showExpenseData(Expense expense);
    }

    interface Presenter extends MvpPresenterRest<View> {
        void handleExpenseData(Expense expense);
    }
}