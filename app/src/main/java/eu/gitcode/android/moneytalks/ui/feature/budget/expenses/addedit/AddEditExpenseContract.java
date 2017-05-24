package eu.gitcode.android.moneytalks.ui.feature.budget.expenses.addedit;

import eu.gitcode.android.moneytalks.models.ui.Expense;
import eu.gitcode.android.moneytalks.ui.common.base.MvpPresenterRest;
import eu.gitcode.android.moneytalks.ui.common.base.MvpViewRest;

public interface AddEditExpenseContract {
    interface View extends MvpViewRest {
        void showExpenseData(Expense expense);

        void showAddOrUpdateSuccessView();
    }

    interface Presenter extends MvpPresenterRest<View> {
        void handleExpenseData(Expense expense);

        void addOrUpdateExpense();
    }
}