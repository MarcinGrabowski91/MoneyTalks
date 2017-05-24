package eu.gitcode.android.moneytalks.ui.feature.budget.expenses.list;

import eu.gitcode.android.moneytalks.ui.common.base.MvpPresenterRest;
import eu.gitcode.android.moneytalks.ui.common.base.MvpViewRest;

public interface ExpensesContract {
    interface View extends MvpViewRest {
        void showExpensesData();
    }

    interface Presenter extends MvpPresenterRest<View> {
        void handleBudgetData();
    }
}